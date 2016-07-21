/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.mosis.entidades.TipoRespuesta;
import com.mosis.entidades.Usuarios;
import com.mosis.negocios.delegate.DelegateTipoRespuesta;
import com.mosis.negocios.delegate.DelegateUsuarios;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Owner
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("servicio_tipo_respuesta")
public class ServicioTipoRespuesta {

    DelegateTipoRespuesta dtr = new DelegateTipoRespuesta();
    DelegateUsuarios du = new DelegateUsuarios();

    @POST
    public Response addTipoRespuesta(@QueryParam("UsuarioID") String UsuarioID, TipoRespuesta tr) {
        TipoRespuesta tipoRespuesta = new TipoRespuesta();

        Usuarios usuarios = du.getUsuarioid(Integer.parseInt(UsuarioID));

        if (usuarios != null) {
            tipoRespuesta.setTipoRespuesta(tr.getTipoRespuesta());
            tipoRespuesta.setFkIdUsuario(usuarios);

            return Response.ok().build();

        } else {
            System.out.println("usuario no valido");
        }
        return null;
    }

    @PUT
    public Response updateTipoRespuesta(@QueryParam("UsuarioID") String UsuarioID, TipoRespuesta tr) {

        TipoRespuesta tipoRespuesta = new TipoRespuesta();

        Usuarios usuarios = du.getUsuarioid(Integer.parseInt(UsuarioID));

        TipoRespuesta tr1 = dtr.getTipoRespuestaId(tr.getIdTipoRespuesta());
        if (tr1 != null) {
            if (usuarios != null) {

                tipoRespuesta.setIdTipoRespuesta(tr.getIdTipoRespuesta());
                tipoRespuesta.setTipoRespuesta(tr.getTipoRespuesta());
                tipoRespuesta.setFkIdUsuario(usuarios);

                return Response.ok().build();

            } else {
                System.out.println("usuario no valido");
            }
        } else {
            System.out.println("tiporespuesta no valido");
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTipoRespuesta(@PathParam("id") int id) {
        dtr.deleteTipoRespuesta(id);
        return Response.ok().build();
    }

}
