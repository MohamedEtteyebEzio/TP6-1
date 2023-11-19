package web;

import entities.Compte;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.CompteRepository;

import java.util.List;
import java.util.Optional;

@Path("/comptes")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository; // Assurez-vous d'injecter CompteRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compte> compteList() {
        return compteRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Compte> getOne(@PathParam("id") Long id) {
        return compteRepository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Compte compte) {
        compteRepository.save(compte);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, Compte compteModifie) {
        Optional<Compte> existingCompte = compteRepository.findById(id);
        if (existingCompte != null) {
            // Mettez à jour les propriétés du compte selon les modifications apportées
            compteRepository.save(compteModifie);
        }
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }
}
