package org.imd.cqrs.sample1.config;

import org.imd.cqrs.sample1.config.profile.ApplicationProfiles;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(ApplicationProfiles.POSTGRES_PROFILE)
@Configuration
public class PostgresConfig {
}
