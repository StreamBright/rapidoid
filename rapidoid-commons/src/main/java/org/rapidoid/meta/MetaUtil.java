package org.rapidoid.meta;

/*
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2017 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Desc;
import org.rapidoid.annotation.Since;
import org.rapidoid.beany.Metadata;
import org.rapidoid.cls.Cls;
import org.rapidoid.commons.Str;
import org.rapidoid.io.IO;
import org.rapidoid.u.U;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
public class MetaUtil extends RapidoidThing {

	public static void generate(ServicesMeta meta, Class<?> serviceClass) {

		ServiceMeta service = new ServiceMeta();

		String name = serviceClass.getSimpleName();

		name = Str.trimr(name, "API");
		name = Str.trimr(name, "MetaModel");

		String instName = name.toLowerCase();

		service.name(name);
		service.inst(instName);

		List<Method> methods = U.list(Cls.getDeclaredMethods(serviceClass));

		for (Method m : methods) {

			if (m.getAnnotation(Skip.class) != null) {
				continue;
			}

			MethodMeta method = new MethodMeta();
			service.methods().add(method);

			String mname = m.getName();
			String rettype = javaType(m.getReturnType());

			method.name(mname);
			method.desc(desc(m.getAnnotations()));
			method.type(rettype);

			String[] names = Cls.getMethodParameterNames(m);
			Class<?>[] types = m.getParameterTypes();
			String[] args = new String[names.length];
			Annotation[][] ann = m.getParameterAnnotations();

			boolean scripted = m.getAnnotation(Implemented.class) != null;

			if (scripted) {
				String code = IO.load(name + "." + mname + ".java");
				code = indentCode(code);
				method.code(code);
			}

			for (int i = 0; i < names.length; i++) {
				ArgMeta arg = new ArgMeta();
				arg.name(names[i]);
				arg.required(Metadata.get(ann[i], Opt.class) == null);

				String type = javaType(types[i]);
				args[i] = U.frmt("%s : %s", names[i], type);

				arg.desc(desc(ann[i]));

				processType(arg, types[i], type);

				method.args().add(arg);
			}

			String syntax = U.frmt("%s.%s(%s) : %s", instName, mname, U.join(", ", args), rettype);
			method.syntax(syntax);

			method.params(U.join(", ", names));

			String paramsTypeName = "";

			for (int i = 0; i < method.args().size(); i++) {
				ArgMeta arg = method.args().get(i);

				if (i > 0) paramsTypeName += ", ";

				paramsTypeName += arg.type + " " + arg.name;
			}

			method.paramsTypeName(paramsTypeName);
		}

		meta.services().add(service);
	}

	private static void processType(TypeMeta meta, Class<?> cls, String type) {

		if (type.endsWith("Params")) {
			meta.type("Object");

			List<OptionMeta> opts = U.list();
			meta.options(opts);

			for (Field f : cls.getDeclaredFields()) {
				OptionMeta field = new OptionMeta();

				field.name(f.getName());
				field.type(javaType(f.getType()));
				field.required(f.getAnnotation(Opt.class) == null);
				field.desc(desc(f.getAnnotations()));

				opts.add(field);
			}

		} else {
			meta.type(type);
		}
	}

	private static String indentCode(String code) {
		String[] lines = code.trim().split("\n");

		for (int i = 0; i < lines.length; i++) {
			lines[i] = "        " + lines[i];
		}

		return U.join("\n", lines);
	}

	private static String desc(Annotation[] annotations) {
		Desc desc = Metadata.get(annotations, Desc.class);
		return (desc != null) ? desc.value() : null;
	}

	private static String javaType(Class<?> type) {
		return type.getSimpleName();
	}

	private static String jsType(Class<?> type) {
		String jsType = type.getSimpleName();

		switch (jsType) {
			case "Dict":
				jsType = "Object";
				break;

			case "Object":
			case "Serializable":
				jsType = "Any";
				break;

			case "byte[]":
				jsType = "Buffer";
				break;
		}

		return jsType;
	}

	public static Map<String, Object> info(ServicesMeta meta, String helpersFilename) {
		Map<String, Object> info = U.map();

		info.put("services", meta.services());

		if (helpersFilename != null) {
			info.put("helpers", IO.load(helpersFilename));
		}

		return info;
	}


}
