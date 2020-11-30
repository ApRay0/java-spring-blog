package com.apray.blog.service;

import com.apray.blog.po.Blog;
import com.apray.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    Blog getAndConvert(Long id);

    Blog saveBlog(Blog blog);



    List<Blog> listRecommendBlogTop(Integer size);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

}
