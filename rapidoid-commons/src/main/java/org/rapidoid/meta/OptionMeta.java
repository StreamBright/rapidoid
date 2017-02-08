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

@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
public class OptionMeta extends RapidoidThing {

	public String name;

	public String type;

	public boolean required;

	public String desc;

	public String optionName() {
		return name;
	}

	public String name() {
		return name;
	}

	public OptionMeta name(String name) {
		this.name = name;
		return this;
	}

	public String type() {
		return type;
	}

	public OptionMeta type(String type) {
		this.type = type;
		return this;
	}

	public boolean required() {
		return required;
	}

	public OptionMeta required(boolean required) {
		this.required = required;
		return this;
	}

	public String desc() {
		return desc;
	}

	public OptionMeta desc(String desc) {
		this.desc = desc;
		return this;
	}
}
