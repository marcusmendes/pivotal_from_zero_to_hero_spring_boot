package com.example.zerotohero.events;

import com.example.zerotohero.github.GithubClient;
import com.example.zerotohero.github.RepositoryEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    private GithubProjectRepository repository;
    private GithubClient githubClient;

    public EventsController(GithubProjectRepository repository, GithubClient githubClient) {
        this.repository = repository;
        this.githubClient = githubClient;
    }

    @GetMapping("/events/{repoName}")
    public RepositoryEvent[] fetchEvents(@PathVariable String repoName) {
        GithubProject project = this.repository.findByRepoName(repoName);
        return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
    }
}
