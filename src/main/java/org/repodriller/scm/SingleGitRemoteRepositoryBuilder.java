package org.repodriller.scm;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;

public class SingleGitRemoteRepositoryBuilder extends GitRemoteRepositoryBuilder {

	private String gitUrl;
	private CredentialsProvider provider;

	public SingleGitRemoteRepositoryBuilder(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public SingleGitRemoteRepositoryBuilder setCredentialsProvider(CredentialsProvider provider) {
		this.provider = provider;
		return this;
	}

	public SingleGitRemoteRepositoryBuilder inTempDir(String tempDir) {
		super.tempDir = tempDir;
		return this;
	}

	public SingleGitRemoteRepositoryBuilder asBareRepos() {
		super.bare = true;
		return this;
	}

	public GitRemoteRepository build() throws GitAPIException {
		return new GitRemoteRepository(this.gitUrl, this.tempDir, provider, this.bare);
	}

	public SCMRepository buildAsSCMRepository() {
		return GitRemoteRepository.singleProject(this.gitUrl, this.tempDir, this.provider, this.bare);
	}

}