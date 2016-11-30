package org.rapidoid.meta;

/*
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
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

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

import java.util.List;

@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
public class ArgMeta implements TypeMeta {

	public String name;

	public String type;

	public String desc;

	public boolean required;

	public List<OptionMeta> options;

	public String argName() {
		return name;
	}

	public String name() {
		return name;
	}

	public ArgMeta name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public void type(String type) {
		this.type = type;
	}

	@Override
	public List<OptionMeta> options() {
		return options;
	}

	@Override
	public void options(List<OptionMeta> options) {
		this.options = options;
	}

	public String desc() {
		return desc;
	}

	public ArgMeta desc(String desc) {
		this.desc = desc;
		return this;
	}

	public boolean required() {
		return required;
	}

	public ArgMeta required(boolean required) {
		this.required = required;
		return this;
	}
}
