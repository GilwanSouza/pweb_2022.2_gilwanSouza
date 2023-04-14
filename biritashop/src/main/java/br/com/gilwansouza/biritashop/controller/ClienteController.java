package br.com.gilwansouza.biritashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gilwansouza.biritashop.model.Cliente;
import br.com.gilwansouza.biritashop.repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepo;

    @GetMapping("/listarCliente")
    public ModelAndView index() {
        List<Cliente> lista = clienteRepo.findAll();
        ModelAndView mav = new ModelAndView("/cliente/listarCliente");
        mav.addObject("clientes", lista);
        return mav;
    }

    @GetMapping("/adicionaCliente")
    public ModelAndView formCadastro() {
        ModelAndView mav = new ModelAndView("/cliente/adicionaCliente");
        mav.addObject(new Cliente());
        return mav;
    }

    @PostMapping("/adicionaCliente")
    public String adicionar(Cliente p) {
        this.clienteRepo.save(p);
        return "redirect:/cliente/listarCliente";
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView formEditar(@PathVariable("id") long id) {
        Cliente cliente = this.clienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        ModelAndView modelAndView = new ModelAndView("/cliente/editarCliente");
        modelAndView.addObject(cliente);
        return modelAndView;
    }

    @PostMapping("/editarCliente/{id}")
    public ModelAndView atualizar(@PathVariable("id") long id, Cliente cliente) {
        this.clienteRepo.save(cliente);
        return new ModelAndView("redirect:/cliente/listarCliente");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable long id) {
        Cliente aRemover = this.clienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        this.clienteRepo.delete(aRemover);
        return new ModelAndView("redirect:/cliente/listarCliente");
    }

}
