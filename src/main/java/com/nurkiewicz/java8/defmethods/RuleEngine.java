package com.nurkiewicz.java8.defmethods;

public class RuleEngine implements Lifecycle,Job,Engine{

        @Override
	public String start() {
		return Job.super.start();
	}
}
