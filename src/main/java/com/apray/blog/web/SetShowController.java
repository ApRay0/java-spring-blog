package com.apray.blog.web;

import com.apray.blog.po.Set;
import com.apray.blog.service.BlogService;
import com.apray.blog.service.SetService;
import com.apray.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SetShowController {

    @Autowired
    private SetService setService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/set/{id}")
    public String sets(@PageableDefault(size=8, sort={"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model, @PathVariable Long id) {
        List<Set> sets = setService.listSetTop(10000);
        if (id == -1) {
            id = sets.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setSetId(id);
        model.addAttribute("sets", sets);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeSetId", id);
        return "set";
    }

}

