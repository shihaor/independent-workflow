package git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author shihaoran
 * @date 2020/2/25
 */
public class GitTest {

    public static void main(String[] args) throws IOException, GitAPIException {
        // 获取路径
        String property = System.getProperty("user.dir");
        Git git = Git.open(new File(property));
        Iterable<RevCommit> call = git.log().call();
        Iterator<RevCommit> iterator = call.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getAuthorIdent().getWhen());
        }
    }
}
