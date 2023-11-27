package org.agoncal.fascicle.quarkus.number;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
class NumberApplicationLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(NumberApplicationLifeCycle.class);

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info(" _   _                 _               ");
    LOGGER.info("| \\ | |               | |              ");
    LOGGER.info("|  \\| |_   _ _ __ ___ | |__   ___ _ __");
    LOGGER.info("| . ` | | | | '_ ` _ \\| '_ \\ / _ \\ '__|");
    LOGGER.info("| |\\  | |_| | | | | | | |_) |  __/ |   ");
    LOGGER.info("\\_| \\_/\\__,_|_| |_| |_|_.__/ \\___|_|   ");
    LOGGER.info("                         Powered by Quarkus");

    LOGGER.info("The application Number is starting with profile " + ProfileManager.getActiveProfile());
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application Number is stopping...");
  }
}
