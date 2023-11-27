package org.agoncal.fascicle.quarkus.number;

import java.time.Instant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import com.github.javafaker.Faker;

@Path("/api/numbers/book")
@Tag(name = "Number Endpoint")
public class NumberResource {
  private static final Logger LOGGER = Logger.getLogger(NumberResource.class);

  @ConfigProperty(name = "number.separator", defaultValue = "false")
  boolean separator;

  @Operation(summary = "Generates book numbers", description = "These book numbers have several formats: ISBN, ASIN and EAN")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BookNumbers.class)))
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response generateBookNumbers() throws InterruptedException {
    LOGGER.info("Generating book numbers");
    Faker faker = new Faker();
    BookNumbers bookNumbers = new BookNumbers();
    bookNumbers.setIsbn10(faker.code().isbn10(separator));
    bookNumbers.setIsbn13(faker.code().isbn13(separator));
    bookNumbers.setAsin(faker.code().asin());
    bookNumbers.setEan8(faker.code().ean8());
    bookNumbers.setEan13(faker.code().ean13());
    bookNumbers.setGenerationDate(Instant.now());
    return Response.ok(bookNumbers).build();
  }
}
