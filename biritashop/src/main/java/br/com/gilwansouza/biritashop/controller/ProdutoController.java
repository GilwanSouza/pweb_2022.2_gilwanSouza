package br.com.gilwansouza.biritashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gilwansouza.biritashop.model.Produto;
import br.com.gilwansouza.biritashop.repository.ProdutoRepository;

@Controller
@RequestMapping

public class ProdutoController {

    @Autowired

    ProdutoRepository produtoRepo;

    @GetMapping("/produtos")

    public ModelAndView index() {
        List<Produto> lista = produtoRepo.findAll();
        ModelAndView mav = new ModelAndView("produtos/index");
        mav.addObject("produtos", lista);
        return mav;
    }

    @GetMapping("/produtos/adicionar")

    public ModelAndView formCadastro() {
        ModelAndView mav = new ModelAndView("produtos/adicionar");
        mav.addObject(new Produto());
        return mav;
    }

    @PostMapping("/produtos/adicionar")

    public String adicionar(Produto p) {
        this.produtoRepo.save(p);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")

    public ModelAndView formEditar(@PathVariable("id") long id) {
        Produto produto = this.produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));

        ModelAndView modelAndView = new ModelAndView("produtos/editar");
        modelAndView.addObject(produto);
        return modelAndView;
    }

    @PostMapping("/produtos/editar/{id}")

    public ModelAndView atualizar(@PathVariable("id") long id, Produto produto) {
        this.produtoRepo.save(produto);
        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/produtos/remover/{id}")

    public ModelAndView remover(@PathVariable long id) {
        Produto aRemover = this.produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        this.produtoRepo.delete(aRemover);
        return new ModelAndView("redirect:/produtos");
    }

}