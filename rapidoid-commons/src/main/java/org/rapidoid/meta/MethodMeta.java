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
import org.rapidoid.annotation.Since;
import org.rapidoid.u.U;

import java.util.List;

@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
public class MethodMeta extends RapidoidThing {

	public String name;

	public String desc;

	public String type;

	public String syntax;

	public String params;

	public String paramsTypeName;

	public String code;

	public List<ArgMeta> args = U.list();

	public RetMeta returns;

	public String methodName() {
		return name;
	}

	public String name() {
		return name;
	}

	public MethodMeta name(String name) {
		this.name = name;
		return this;
	}

	public List<ArgMeta> args() {
		return args;
	}

	public MethodMeta args(List<ArgMeta> args) {
		this.args = args;
		return this;
	}

	public String desc() {
		return desc;
	}

	public MethodMeta desc(String desc) {
		this.desc = desc;
		return this;
	}

	public String type() {
		return type;
	}

	public MethodMeta type(String type) {
		this.type = type;
		return this;
	}

	public String syntax() {
		return syntax;
	}

	public MethodMeta syntax(String syntax) {
		this.syntax = syntax;
		return this;
	}

	public String params() {
		return params;
	}

	public MethodMeta params(String params) {
		this.params = params;
		return this;
	}

	public String paramsTypeName() {
		return paramsTypeName;
	}

	public MethodMeta paramsTypeName(String paramsTypeName) {
		this.paramsTypeName = paramsTypeName;
		return this;
	}

	public RetMeta returns() {
		return returns;
	}

	public MethodMeta returns(RetMeta returns) {
		this.returns = returns;
		return this;
	}

	public String code() {
		return code;
	}

	public MethodMeta code(String code) {
		this.code = code;
		return this;
	}

	public boolean returnsResult() {
		return U.neq(type, "void");
	}

}
