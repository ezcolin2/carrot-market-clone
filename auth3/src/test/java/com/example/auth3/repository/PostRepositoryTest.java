package com.example.auth3.repository;

import com.example.auth3.entity.Member;
import com.example.auth3.entity.Post;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Post post = Post.builder()
                .postTitle("title")
                .price(1000)
                .content("content")
                .region("region").build();
        Post save = postRepository.save(post);
        org.assertj.core.api.Assertions.assertThat(post.getPostTitle()).isEqualTo(save.getPostTitle());


    }

    @Test
    void findById() {
        Post post1 = Post.builder().postTitle("title1").build();
        Post post2 = Post.builder().postTitle("title2").build();
        Post post3 = Post.builder().postTitle("title3").build();
        Post post4 = Post.builder().postTitle("title4").build();
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        List<Post> all = postRepository.findAll();
        all.stream().iterator().forEachRemaining(
                e -> org.assertj.core.api.Assertions.assertThat(e.getPostTitle())
                        .isEqualTo("title" + e.getId().toString())
        );
    }

    @Test
    void findByTitle() {
        Post post1 = Post.builder().postTitle("title").build();
        Post post2 = Post.builder().postTitle("title").build();
        Post post3 = Post.builder().postTitle("title1").build();
        Post post4 = Post.builder().postTitle("title2").build();
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        List<Post> title = postRepository.findByTitle("title1", 0L, 10L);
        org.assertj.core.api.Assertions.assertThat(title.size()).isEqualTo(1);

    }

    @Test
    void findByUserId() {
        Member member = new Member();
        memberRepository.save(member);
        Post post1 = Post.builder().member(member).build();
        Post post2 = Post.builder().member(member).build();
        Post post3 = Post.builder().member(member).build();
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }

//    @Test
//    void findInterestsByWriterId() {
//        Member member = Member.builder().memberEmail("test@gmail.com").build();
//        memberRepository.save(member);
//        Post post1 = Post.builder().member(member).build();
//        Post post2 = Post.builder().member(member).build();
//        Post post3 = Post.builder().member(member).build();
//        postRepository.save(post1);
//        postRepository.save(post2);
//        postRepository.save(post3);
//        List<Post> list = postRepository.findInterestsByWriterId(member.getId(), 0L, 10L);
//        Assertions.assertThat(list.size()).isEqualTo(3);
//    }
}