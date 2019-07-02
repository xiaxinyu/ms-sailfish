package com.sailfish.utils;

import com.sailfish.exception.AppException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Git Utils
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class GitUtils {
    private static final Logger logger = LoggerFactory.getLogger(GitUtils.class);

    /**
     * init local repository
     *
     * @param localDir source code directory
     * @return
     */
    public static Git initLocalRepository(File localDir) {
        long t1 = System.currentTimeMillis();
        Git git;
        try {
            logger.info("Init local repository, localDir={}", localDir.getAbsolutePath());
            git = Git.init().setDirectory(localDir).call();
            logger.info("Finish init local repository, localDir={}", localDir.getAbsolutePath());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        logger.info("initLocalRepository costs {} seconds", (t2 - t1) / 1000);
        return git;
    }

    /**
     * clone repository to local
     *
     * @param workDir     work directory
     * @param remoteUrl   remote url to clone
     * @param accessToken access token
     * @return the git instance of local repository
     */
    public static Git cloneRepository(String workDir, String remoteUrl, String accessToken) {
        Git git;
        File localDir = new File(workDir);
        deleteDirectory(localDir);
        try {
            logger.info("Cloning repository to local, workDir={}, remoteUrl={}", workDir, remoteUrl);
            Git.cloneRepository()
                    .setURI(remoteUrl)
                    .setBranch("")
                    .setCredentialsProvider(StringUtils.isEmpty(accessToken) ? null : new UsernamePasswordCredentialsProvider("", accessToken))
                    .setDirectory(localDir)
                    .call();
            FileUtils.deleteDirectory(new File(localDir.getAbsolutePath() + File.separator + ".git"));
            git = initLocalRepository(localDir);
            logger.info("Finish clone repository to local, workDir={}, remoteUrl={}", workDir, remoteUrl);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        return git;
    }

    /**
     * commit and push to remote repository
     *
     * @param git         local git
     * @param repoUrl     repository address
     * @param accessToken token access token
     */
    public static void commitAndPush(Git git, String repoUrl, String accessToken) {
        long t1 = System.currentTimeMillis();
        try {
            logger.info("Committing and pushing local repository, repoUrl={}", repoUrl);
            String[] url = repoUrl.split("://");
            git.add().addFilepattern(".").call();
            git.add().setUpdate(true).addFilepattern(".").call();
            git.commit().setMessage("init").call();
            List<Ref> refs = git.branchList().call();
            PushCommand pushCommand = git.push();
            for (Ref ref : refs) {
                pushCommand.add(ref);
            }
            pushCommand.setRemote(repoUrl);
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(
                    "", accessToken));
            pushCommand.call();
            logger.info("Finish committing and pushing local repository, repoUrl={}", repoUrl);
        } catch (GitAPIException e) {
            throw new AppException(e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        logger.info("commitAndPush costs {} seconds", (t2 - t1) / 1000);
    }

    /**
     * delete assigned directory
     *
     * @param dir
     */
    public static void deleteDirectory(File dir) {
        if (dir.exists()) {
            try {
                logger.info("Deleting directory, dir={}", dir);
                FileUtils.deleteDirectory(dir);
                logger.info("Finish deleting directory, dir={}", dir);
            } catch (IOException e) {
                throw new AppException(e.getMessage());
            }
        } else {
            logger.warn("Deleting directory doesn't exist. dir={}", dir.getAbsoluteFile());
        }
    }
}
