package br.com.gilwansouza.biritashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gilwansouza.biritashop.model.Pedido;
import br.com.gilwansouza.biritashop.model.Produto;
import br.com.gilwansouza.biritashop.repository.PedidoRepository;
import br.com.gilwansouza.biritashop.repository.ProdutoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController { 

    @Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ProdutoRepository produtoRepo;

    @GetMapping("/listarPedido")
    public ModelAndView index() {
        List<Pedido> lista = pedidoRepo.findAll();
        ModelAndView mav = new ModelAndView("/pedido/listarPedido");
        mav.addObject("pedidos", lista);
        return mav;
    }

    @GetMapping("/adicionarPedido")
    public ModelAndView formCadastro() {
        List<Produto> listaProduto = produtoRepo.findAll();
        ModelAndView mav = new ModelAndView("/pedido/adicionarPedido");
        mav.addObject("produtos", listaProduto);
        mav.addObject(new Pedido());
        return mav;
    }

    @PostMapping("/adicionarPedido")
    public String adicionar(Pedido p) {
        this.pedidoRepo.save(p);
        return "redirect:/pedido/listarPedido";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formEditar(@PathVariable("id") long id) {
        Pedido pedido = this.pedidoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));

        ModelAndView modelAndView = new ModelAndView("/pedido/editarPedido");
        modelAndView.addObject(pedido);
        return modelAndView;
    }

    @PostMapping("/editarPedido/{id}")
    public ModelAndView atualizar(@PathVariable("id") long id, Pedido pedido) {
        this.pedidoRepo.save(pedido);
        return new ModelAndView("redirect:/pedido/listarPedido");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable long id) {
        Pedido aRemover = this.pedidoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        this.pedidoRepo.delete(aRemover);
        return new ModelAndView("redirect:/pedido/listarPedido");
    }

}