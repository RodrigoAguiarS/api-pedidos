package br.com.rodrigo.api.pedidos.rest;

import br.com.rodrigo.api.pedidos.model.form.PerfilForm;
import br.com.rodrigo.api.pedidos.model.response.PerfilResponse;
import br.com.rodrigo.api.pedidos.services.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfis")
public class PerfilController extends GenericControllerImpl<PerfilForm, PerfilResponse> {

    protected PerfilController(GenericService<PerfilForm, PerfilResponse> service) {
        super(service);
    }
}
