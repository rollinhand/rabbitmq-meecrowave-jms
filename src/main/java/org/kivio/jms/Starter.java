package org.kivio.jms;

import org.apache.meecrowave.Meecrowave;

public class Starter {
	public static void main(String[] args) {
		Meecrowave.Builder builder = new Meecrowave.Builder();
		builder.setHttpPort(8080);
		
		try(final Meecrowave meecrowave = new Meecrowave(builder)) {
			meecrowave.bake().await();
		}
	}
}
