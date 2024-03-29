package br.com.gilwansouza.biritashop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.gilwansouza.biritashop.model.Produto;
import br.com.gilwansouza.biritashop.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepo;

    @GetMapping("/listarProduto")
    public ModelAndView index() {
        List<Produto> lista = produtoRepo.findAll();
        ModelAndView mav = new ModelAndView("/produto/listarProduto");
        mav.addObject("produtos", lista);
        return mav;
    }

    @GetMapping("/adicionarProduto")
    public ModelAndView formCadastro() {
        ModelAndView mav = new ModelAndView("/produto/adicionarProduto");
        mav.addObject(new Produto());
        return mav;
    }

    @PostMapping("/adicionarProduto")
    public String cadastrarProduto(@ModelAttribute Produto produto,
            @RequestParam("imagem") MultipartFile imagem) {
        if (!imagem.isEmpty()) {
            byte[] bytes = new byte[(int) imagem.getSize()];
            try {
                bytes = imagem.getBytes();
                produto.setImagemProduto(bytes);
                this.produtoRepo.save(produto);
            } catch (Exception exception) {
            }
        }
        return "redirect:/produto/listarProduto";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formEditar(@PathVariable("id") long id) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));

        ModelAndView modelAndView = new ModelAndView("/produto/editarProduto");
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @PostMapping("/editarProduto/{id}")
    public ModelAndView atualizar(@PathVariable("id") long id, @ModelAttribute("produto") Produto produto,
            @RequestParam("imagem") MultipartFile imagem) {
        if (!imagem.isEmpty()) {
            try {
                byte[] bytes = imagem.getBytes();
                produto.setImagemProduto(bytes);
            } catch (IOException e) {
            }
        }
        produtoRepo.save(produto);
        return new ModelAndView("redirect:/produto/listarProduto");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable long id) {
        Produto aRemover = this.produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        this.produtoRepo.delete(aRemover);
        return new ModelAndView("redirect:/produto/listarProduto");
    }
}