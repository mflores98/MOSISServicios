/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.Guardias;
import com.mosis.negocios.integracion.ServiceFacadeLocator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author Owner
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("servicio_guardia")
public class ServicioGuardia {
    /*
     { 
     "nombre": "Jose",
     "ap": "ortiz",
     "am": "Perez",
     "fechaNacimiento": "1992",
     "numeroEmpleado": "654654"
     }
     */

    // http://localhost:8084/MOSISServicios/webresources/servicio_guardia
    @POST
    public Response addGuardia(Guardias g) {
        Guardias guardias = new Guardias();
        guardias.setNombre(g.getNombre());
        guardias.setAp(g.getAp());
        guardias.setAm(g.getAm());
        guardias.setFechaNacimiento(g.getFechaNacimiento());
        guardias.setNumeroEmpleado(g.getNumeroEmpleado());
        ServiceFacadeLocator.getFacadeGuardia().addGuardia(guardias);
        return Response.ok(guardias).build();
    }

//    public static void main(String[] args) {
//        ServicioGuardia servicioGuardia=new ServicioGuardia();
//        Guardias guardias=new Guardias(null, "Fernando", "Jimenez", "Perez", "8/8/1999", "984165");    
//        servicioGuardia.addGuardia(guardias);
//    }
    @PUT
    @Path("/{id}")
    public Response updateGuardia(@PathParam("id") int id, Guardias g) {
        Guardias guardias = new Guardias();
        guardias.setNombre(g.getNombre());
        guardias.setAp(g.getAp());
        guardias.setAm(g.getAm());
        guardias.setFechaNacimiento(g.getFechaNacimiento());
        guardias.setNumeroEmpleado(g.getNumeroEmpleado());
        ServiceFacadeLocator.getFacadeGuardia().updateDatNumEmpGuardia(id, guardias);
        return Response.ok(guardias).build();
    }

    // http://localhost:8084/MOSISServicios/webresources/servicio_guardia/2
    @Path("/{id}")
    @GET
    public StreamingOutput guardiaId(@PathParam("id") int id) {

        Guardias dato = ServiceFacadeLocator.getFacadeGuardia().getGuardiaId(id);
        return new StreamingOutput() {

            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaGuardiaId(out, dato);
            }
        };
    }

    protected void salidaGuardiaId(OutputStream os, Guardias dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String string = gson.toJson(dato);
        printStream.println(string);
        os.flush();
    }

//    public static void main(String[] args) {
//        ServicioGuardia sg = new ServicioGuardia();
//        System.out.println(sg.guardiaId(2));
//    }
    @GET
    public StreamingOutput getGuardias() {
        List<Guardias> dato = ServiceFacadeLocator.getFacadeGuardia().getListGuardias();
        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaGuardias(out, dato);
            }
        };
    }

    protected void salidaGuardias(OutputStream os, List<Guardias> dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String string = gson.toJson(dato);
        printStream.println(string);
        os.flush();
    }

//    public static void main(String[] args) {
//        ServicioGuardia sg=new ServicioGuardia();
//         StreamingOutput s= sg.getGuardias();
//        System.out.println(s);
//    }

    @DELETE
    @Path("/{id}")
    public Response deleteGuardia(@PathParam("id") int id) {
        ServiceFacadeLocator.getFacadeGuardia().deleteGuardia(id);
        return Response.ok(id).build();
    }
//    public static void main(String[] args) {
//        ServicioGuardia sg=new ServicioGuardia();
//        sg.deleteGuardia(5);
//    }

}
