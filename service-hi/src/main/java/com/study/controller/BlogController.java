package com.study.controller;

import com.study.model.BlogModel;
import com.study.repository.BlogRepository;
import com.study.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author YanCh
 * Create by 2020-02-12 17:31
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @PostMapping("/add")
    public Result add(BlogModel blogModel){
        blogRepository.save(blogModel);
        return Result.success(blogModel);
    }

    @PostMapping("/get/{id}")
    public Result getById(@PathVariable String id){
        Optional<BlogModel> blogModel = blogRepository.findById(id);
        if (blogModel.isPresent()) {
            BlogModel model = blogModel.get();
            return Result.success(model);
        }
        return Result.success();
    }

    @PostMapping("/getAll")
    public Result getAll(){
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return Result.success(list);
    }
    @PostMapping("/update")
    public Result update(BlogModel blogModel){
        blogRepository.save(blogModel);
        return Result.success();
    }
    @PostMapping("/del/{id}")
    public Result delById(@PathVariable String id){
        blogRepository.deleteById(id);
        return Result.success();
    }

    @PostMapping("/getByTitle")
    public Result getByTitle(@RequestParam String keyword){
        return Result.success(blogRepository.findByTimeLike(keyword));
    }
}
