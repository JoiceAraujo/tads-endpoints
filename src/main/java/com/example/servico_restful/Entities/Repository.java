package com.example.servico_restful.Entities;

import com.example.servico_restful.Entities.PojoEntities.RepositoryPojo;

import java.util.Date;



public class Repository {
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

    public String id;
    public int assignable_users;
    public String code_of_conduct;
    public Date created_at;
    public int database_id;
    public String default_branch;
    public boolean delete_branch_on_merge;
    public String description;
    public int disk_usage;
    public int forks;
    public boolean has_issues_enabled;
    public boolean has_projects_enabled;
    public boolean has_wiki_enabled;
    public String homepage_url;
    public boolean is_archived;
    public boolean is_blank_issues_enabled;
    public boolean is_disabled;
    public boolean is_empty;
    public boolean is_fork;
    public boolean is_in_organization;
    public boolean is_locked;
    public boolean is_mirror;
    public boolean is_private;
    public boolean is_security_policy_enabled;
    public boolean is_template;
    public boolean is_user_configuration_repository;
    public int issues;
    public int labels;
    public String languages;
    public String license_info;
    public int mentionable_users;
    public boolean merge_commit_allowed;
    public int milestones;
    public String name;
    public String name_with_owner;
    public String open_graph_image_url;
    public Actor owner;
    public String primary_language;
    public Date pushed_at;
    public int pull_requests;
    public boolean rebase_merge_allowed;
    public int releases;
    public String repository_topics;
    public boolean squash_merge_allowed;
    public int stargazers;
    public int tags;
    public Date updated_at;
    public String url;
    public boolean uses_custom_open_graph_image;
    public int vulnerability_alerts;
    public int watchers;
}