/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.Preguntas;
import com.mosis.entidades.Usuarios;
import com.mosis.negocios.delegate.DelegatePreguntas;
import com.mosis.negocios.delegate.DelegateUsuarios;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
@Path("servicio_pregunta")
public class ServicioPregunta {

    DelegatePreguntas dp = new DelegatePreguntas();
    DelegateUsuarios du = new DelegateUsuarios();
    /*
     ?UsuarioID=19 
     { "pregunta":"pregunta prueba por post"
     
     }
     */

    /**
     *
     * @param UsuarioID
     * @param p
     * @return
     */
    @POST
    public Response addPregunta(@QueryParam("UsuarioID") String UsuarioID, Preguntas p) {
        Preguntas preguntas = new Preguntas();
        Usuarios usuarios = du.getUsuarioid(Integer.parseInt(UsuarioID));
        if (usuarios != null) {
            preguntas.setPregunta(p.getPregunta());
            preguntas.setFkIdUsuario(usuarios);
            dp.addPreguntas(preguntas, usuarios);
        } else {
            System.out.println("usuario no valido");
        }
        return Response.ok().build();
    }
    /*
     {  
     "idPregunta":5,
     "pregunta":"pregunta prueba por put"
     }
     */

    @PUT
    public Response updatePregunta(@QueryParam("UsuarioID") String UsuarioID, Preguntas p) {
        Preguntas preguntas = new Preguntas();
        Usuarios usuarios = du.getUsuarioid(Integer.parseInt(UsuarioID));

        Preguntas preguntasID = dp.getPreguntaId(p.getIdPregunta());
        if (usuarios != null) {
            if (preguntasID != null) {
                preguntas.setIdPregunta(p.getIdPregunta());
                preguntas.setPregunta(p.getPregunta());
                preguntas.setFkIdUsuario(usuarios);
                dp.updatePreguntas(preguntas, usuarios);
                return Response.ok().build();

            } else {
                System.out.println("pregunta no valido");
            }
        } else {
            System.out.println("usuario no valido");
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePregunta(@PathParam("id") String id) {
        dp.deletePreguntas(Integer.parseInt(id));
        return Response.ok().build();
    }
    /*
     @return Estado HTTP 500 - java.lang.UnsupportedOperationException:
     Attempted to serialize java.lang.Class:
     org.hibernate.proxy.HibernateProxy. Forgot to register a type adapter? 
     */

    @GET
    @Path("/{id}")
    public StreamingOutput getPreguntaId(@PathParam("id") int id) {
        Preguntas dato = (Preguntas) dp.getListPreguntas();
        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaPregunta(out, dato);
            }
        };
    }

    protected void salidaPregunta(OutputStream os, Preguntas dato) throws IOException {
        PrintStream printStream = new PrintStream(os);

        Gson gson = new Gson();
        String string = gson.toJson(dato);
        printStream.println(string);
        os.flush();
    }
    /*
     @return Estado HTTP 500 - java.lang.UnsupportedOperationException:
     Attempted to serialize java.lang.Class:
     org.hibernate.proxy.HibernateProxy. Forgot to register a type adapter? 
     */

    @GET
    public StreamingOutput getPreguntas() {
        List<Preguntas> dato = dp.getListPreguntas();

        return new StreamingOutput() {

            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaPreguntas(out, dato);
            }
        };
    }

    protected void salidaPreguntas(OutputStream os, List<Preguntas> dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String string = gson.toJson(dato);
        printStream.println(string);
        os.flush();
    }

}
