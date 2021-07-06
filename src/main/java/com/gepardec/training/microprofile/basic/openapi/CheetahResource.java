package com.gepardec.training.microprofile.basic.openapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/cheetahs")
public class CheetahResource {

    private Set<Cheetah> cheetahs = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public CheetahResource() {
        cheetahs.add(new Cheetah(1, "Nala", 30.4));
        cheetahs.add(new Cheetah(6, "Simba", 54.6));
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Set<Cheetah> list() {
        return cheetahs;
    }

    @Path("/getResponse")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listResponse() {
        return Response.ok(cheetahs).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Set<Cheetah> add(Cheetah cheetah) {
        cheetahs.add(cheetah);
        return cheetahs;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    public Set<Cheetah> delete(Cheetah cheetah) {
        cheetahs.removeIf(existingCheetah -> existingCheetah.getName().equalsIgnoreCase(cheetah.getName()));
        return cheetahs;
    }

}