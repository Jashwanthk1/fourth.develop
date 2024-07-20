package com.office.fourth.develop.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BuildInfoProvider {

	public static final String BUILD_TIME_PROPERTY = "build.time";

	public static final String BUILD_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss z";

	public static final String BUILD_ERROR_MESSAGE = "Rebuild application to generate build info";

	private final Properties properties;

	public BuildInfoProvider() {
		this.properties = loadBuildProperties();
	}

	private Properties loadBuildProperties() {
		Properties props = new Properties();
		try (InputStream stream = new ClassPathResource("/META-INF/build-info.properties").getInputStream()) {
			props.load(stream);
		} catch (IOException e) {
			log.error("Load Build Properties : {}", e.getMessage());
			props.clear();
			props.setProperty(BUILD_TIME_PROPERTY, "Rebuild application to generate build info");
		}
		return props;
	}

	public String getBuildTimestamp() {
		return getBuildTimestamp("UTC");
	}

	public String getBuildTimestamp(String zone) {
		if (zone.isEmpty() || zone.isBlank())
			return getBuildTimestamp();
		String response = properties.getProperty(BUILD_TIME_PROPERTY);
		try {
			ZonedDateTime utcDateTime = ZonedDateTime.parse(response, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			ZoneId istZone = ZoneId.of(zone.isBlank() ? "UTC" : zone);
			ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(istZone);
			response = istDateTime.format(DateTimeFormatter.ofPattern(BUILD_TIMESTAMP_FORMAT));
		} catch (Exception e) {
			return response;
		}
		return response;
	}

	public String getBuildTimestamp(String zone1, String zone2) {
		String responseZone1 = getBuildTimestamp(zone1);
		String responseZone2 = getBuildTimestamp(zone2);
		String buildResponse = "Last build : ";
		String response = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BUILD_TIMESTAMP_FORMAT);
		try {
			ZonedDateTime.parse(responseZone1, formatter);
			ZonedDateTime.parse(responseZone2, formatter);
			response = responseZone1 + " " + responseZone2;
		} catch (Exception e) {
			response = BUILD_ERROR_MESSAGE;
		}
		buildResponse = buildResponse + response;
		return buildResponse;
	}

}