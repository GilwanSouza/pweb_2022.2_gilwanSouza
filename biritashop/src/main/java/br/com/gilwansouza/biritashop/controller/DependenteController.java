package br.com.gilwansouza.biritashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gilwansouza.biritashop.model.Dependente;
import br.com.gilwansouza.biritashop.repository.DependenteRepository;

@Controller
@RequestMapping("/dependente")
public class DependenteController {

    @Autowired
    DependenteRepository dependenteRepo;

    @GetMapping("/listarDependente")
    public ModelAndView index() {
        List<Dependente> lista = dependenteRepo.findAll();
        ModelAndView mav = new ModelAndView("/dependente/listarDependente");
        mav.addObject("dependente", lista);
        return mav;
    }

    @GetMapping("/adicionarDependente")
    public ModelAndView formCadastro() {
        ModelAndView mav = new ModelAndView("/dependente/adicionarDependente");
        mav.addObject(new Dependente());
        return mav;
    }

    @PostMapping("/adicionarDependente")
    public String adicionar(Dependente p) {
        this.dependenteRepo.save(p);
        return "redirect:/produto";
    }

    @GetMapping("/editarDependente/{id}")
    public ModelAndView formEditar(@PathVariable("id") long id) {
        Dependente dependente = this.dependenteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));

        ModelAndView modelAndView = new ModelAndView("/dependente/editarDependente");
        modelAndView.addObject(dependente);
        return modelAndView;
    }

    @PostMapping("/editarDependente/{id}")
    public ModelAndView atualizar(@PathVariable("id") long id, Dependente dependente) {
        this.dependenteRepo.save(dependente);
        return new ModelAndView("redirect:/dependente/listarDependente");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable long id) {
        Dependente remover = this.dependenteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        this.dependenteRepo.delete(remover);
        return new ModelAndView("redirect:/dependente/listarDependente");
    }

}