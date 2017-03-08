package org.rapidoid.meta;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
@Documented
public @interface Returns {
	String value();
}
