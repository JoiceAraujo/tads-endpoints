package com.example.servico_restful.PojoModels;

import java.util.Date;

public class RepositoryPojo {
    public boolean isNameRepositoryEqualsParam(String param) {
        if(param.isBlank() || param.isEmpty()) return false;

        if (this.name != null && !this.name.isEmpty()) {
            return this.name.contains(param);
        }

        return false;
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
    public String funding_links;
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
    public Object lock_reason;
    public int mentionable_users;
    public boolean merge_commit_allowed;
    public int milestones;
    public Object mirror_url;
    public String name;
    public String name_with_owner;
    public String open_graph_image_url;
    public String owner;
    public Object parent;
    public String primary_language;
    public Date pushed_at;
    public int pull_requests;
    public boolean rebase_merge_allowed;
    public int releases;
    public String repository_topics;
    public boolean squash_merge_allowed;
    public int stargazers;
    public int tags;
    public Object template_repository;
    public Date updated_at;
    public String url;
    public boolean uses_custom_open_graph_image;
    public int vulnerability_alerts;
    public int watchers;
}
