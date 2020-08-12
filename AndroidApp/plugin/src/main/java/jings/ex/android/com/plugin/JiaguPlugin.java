package jings.ex.android.com.plugin;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class JiaguPlugin implements  Plugin<Project>{
    @Override
    public void apply(Project project) {

        final JiaguExt jiagu = project.getExtensions().create("jiagu",JiaguExt.class);

        //????
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("jiagu username="+jiagu.getUserName());
            }
        });
    }
}
