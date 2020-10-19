package com.data.example.data_example.bootstrap;

import com.data.example.data_example.model.Tweet;
import com.data.example.data_example.model.Group;
import com.data.example.data_example.model.User;
import com.data.example.data_example.model.UserProfile;
import com.data.example.data_example.repositories.TweetRepository;
import com.data.example.data_example.repositories.GroupRepository;
import com.data.example.data_example.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Fetch;
import org.hibernate.loader.plan.exec.internal.LoadQueryJoinAndFetchProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final TweetRepository tweetRepository;

    public BootstrapData(UserRepository userRepository,
                         GroupRepository groupRepository,
                         TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User alice = new User("Alice", "Bobson");
        Group  cats = new Group("cats", "We love cats");
        alice.getGroups().add(cats);
        cats.getUsers().add(alice);

        Tweet tweet = new Tweet();
        tweet.setCity("New York");
        tweet.setCountry("USA");
        tweet.setEmail("lol@gmail.com");
        tweet.setTitle("alice_the_best");
        tweet.setState("New Jersey");
        tweet.setPassword("1234");
        tweet.setUser(alice);

        alice.getTweets().add(tweet);

        tweetRepository.save(tweet);
        userRepository.save(alice);
        groupRepository.save(cats);

        log.info("Count of tweet = " + tweetRepository.count());
        log.info("count of groups = " + groupRepository.count());
        log.info("Tweets size for user alice = " + userRepository.findById(1L).get().getTweets().size());
        log.info(userRepository.findAll());
    }
}
