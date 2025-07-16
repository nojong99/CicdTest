package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    
    private final PostService postService;
    private final JwtTokenProvider tokenProvider;
    
    public PostController(PostService postService, JwtTokenProvider tokenProvider) {
        this.postService = postService;
        this.tokenProvider = tokenProvider;
    }
    
    @GetMapping
    public ResponseEntity<Page<PostDto.PostListResponse>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostDto.PostListResponse> posts = postService.getAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<PostDto.PostListResponse>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostDto.PostListResponse> posts = postService.searchPosts(keyword, pageable);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto.PostResponse> getPostById(@PathVariable Long id) {
        PostDto.PostResponse post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto.CreateRequest request,
                                       @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String username = tokenProvider.getUsernameFromToken(jwt);
            PostDto.PostResponse post = postService.createPost(request, username);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,
                                       @Valid @RequestBody PostDto.UpdateRequest request,
                                       @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String username = tokenProvider.getUsernameFromToken(jwt);
            PostDto.PostResponse post = postService.updatePost(id, request, username);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id,
                                       @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String username = tokenProvider.getUsernameFromToken(jwt);
            postService.deletePost(id, username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 