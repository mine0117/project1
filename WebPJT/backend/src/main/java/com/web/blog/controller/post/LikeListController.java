package com.web.blog.controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.web.blog.dao.post.LikeListDao;
import com.web.blog.model.post.LikeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/like")
public class LikeListController {
    
    @Autowired
    LikeListDao likeListDao;

    @GetMapping("/list")
    @ApiOperation("좋아요 리스트")
    public List<LikeList> selectAll() throws SQLException, IOException {
        List<LikeList> list = new LinkedList<>();

        list = likeListDao.findAll();

        return list;
    }

    @PutMapping("/regist")
    @ApiOperation("좋아요 등록")
    public Object regist(@RequestBody LikeList request) throws SQLException, IOException {
        try {
            LikeList list = new LikeList();
            list.setUid(request.getUid());
            list.setEmail(request.getEmail());
            likeListDao.save(list);

            return list;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{no}")
    @ApiOperation("좋아요 삭제")
    public String delete(int no) throws SQLException, IOException {
        likeListDao.delete(likeListDao.findByNo(no));
        return "좋아요 삭제 완료";
    }
}