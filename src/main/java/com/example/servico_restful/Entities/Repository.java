package com.example.servico_restful.Entities;

import com.example.servico_restful.Entities.PojoEntities.RepositoryPojo;

import java.util.Date;

public class Repository {
    private final String id;
    private final int assignable_users;
    private final String code_of_conduct;
    private final Date created_at;
    private final int database_id;
    private final String default_branch;
    private final boolean delete_branch_on_merge;
    private final String description;
    private final int disk_usage;
    private final int forks;
    private final boolean has_issues_enabled;
    private final boolean has_projects_enabled;
    private final boolean has_wiki_enabled;
    private final String homepage_url;
    private final boolean is_archived;
    private final boolean is_blank_issues_enabled;
    private final boolean is_disabled;
    private final boolean is_empty;
    private final boolean is_fork;
    private final boolean is_in_organization;
    private final boolean is_locked;
    private final boolean is_mirror;
    private final boolean is_private;
    private final boolean is_security_policy_enabled;
    private final boolean is_template;
    private final boolean is_user_configuration_repository;
    private final int issues;
    private final int labels;
    private final String languages;
    private final String license_info;
    private final int mentionable_users;
    private final boolean merge_commit_allowed;
    private final int milestones;
    private final String name;
    private final String name_with_owner;
    private final String open_graph_image_url;
    private Actor owner;
    private final String primary_language;
    private final Date pushed_at;
    private final int pull_requests;
    private final boolean rebase_merge_allowed;
    private final int releases;
    private final String repository_topics;
    private final boolean squash_merge_allowed;
    private final int stargazers;
    private final int tags;
    private final Date updated_at;
    private final String url;
    private final boolean uses_custom_open_graph_image;
    private final int vulnerability_alerts;
    private final int watchers;

    public Repository(RepositoryPojo repositoryPojo) {
        this.id = repositoryPojo.id;
        this.assignable_users = repositoryPojo.assignable_users;
        this.code_of_conduct = repositoryPojo.code_of_conduct;
        this.created_at = repositoryPojo.created_at;
        this.database_id = repositoryPojo.database_id;
        this.default_branch = repositoryPojo.default_branch;
        this.delete_branch_on_merge = repositoryPojo.delete_branch_on_merge;
        this.description = repositoryPojo.description;
        this.disk_usage = repositoryPojo.disk_usage;
        this.forks = repositoryPojo.forks;
        this.has_issues_enabled = repositoryPojo.has_issues_enabled;
        this.has_projects_enabled = repositoryPojo.has_projects_enabled;
        this.has_wiki_enabled = repositoryPojo.has_wiki_enabled;
        this.homepage_url = repositoryPojo.homepage_url;
        this.is_archived = repositoryPojo.is_archived;
        this.is_blank_issues_enabled = repositoryPojo.is_blank_issues_enabled;
        this.is_disabled = repositoryPojo.is_disabled;
        this.is_empty = repositoryPojo.is_empty;
        this.is_fork = repositoryPojo.is_fork;
        this.is_in_organization = repositoryPojo.is_in_organization;
        this.is_locked = repositoryPojo.is_locked;
        this.is_mirror = repositoryPojo.is_mirror;
        this.is_private = repositoryPojo.is_private;
        this.is_security_policy_enabled = repositoryPojo.is_security_policy_enabled;
        this.is_template = repositoryPojo.is_template;
        this.is_user_configuration_repository = repositoryPojo.is_user_configuration_repository;
        this.issues = repositoryPojo.issues;
        this.labels = repositoryPojo.labels;
        this.languages = repositoryPojo.languages;
        this.license_info = repositoryPojo.license_info;
        this.mentionable_users = repositoryPojo.mentionable_users;
        this.merge_commit_allowed = repositoryPojo.merge_commit_allowed;
        this.milestones = repositoryPojo.milestones;
        this.name = repositoryPojo.name;
        this.name_with_owner = repositoryPojo.name_with_owner;
        this.open_graph_image_url = repositoryPojo.open_graph_image_url;
        this.primary_language = repositoryPojo.primary_language;
        this.pushed_at = repositoryPojo.pushed_at;
        this.pull_requests = repositoryPojo.pull_requests;
        this.rebase_merge_allowed = repositoryPojo.rebase_merge_allowed;
        this.releases = repositoryPojo.releases;
        this.repository_topics = repositoryPojo.repository_topics;
        this.squash_merge_allowed = repositoryPojo.squash_merge_allowed;
        this.stargazers = repositoryPojo.stargazers;
        this.tags = repositoryPojo.tags;
        this.updated_at = repositoryPojo.updated_at;
        this.url = repositoryPojo.url;
        this.uses_custom_open_graph_image = repositoryPojo.uses_custom_open_graph_image;
        this.vulnerability_alerts = repositoryPojo.vulnerability_alerts;
        this.watchers = repositoryPojo.watchers;
        this.owner = new Actor(repositoryPojo.owner);
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }

    public Actor getOwner() {
        return owner;
    }
}