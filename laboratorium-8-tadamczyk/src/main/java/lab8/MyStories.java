package lab8;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;

import lab8.steps.MySteps;

public class MyStories extends JUnitStories {

  public MyStories() {
    configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
        .doIgnoreFailureInView(true).useThreads(2).useStoryTimeoutInSecs(60);
  }

  @Override
  public Configuration configuration() {
    Class<? extends Embeddable> embeddableClass = this.getClass();
    ParameterConverters parameterConverters = new ParameterConverters();
    ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
        new LoadFromClasspath(embeddableClass), parameterConverters, null, new TableTransformers());
    parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
        new ExamplesTableConverter(examplesTableFactory));
    return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass))
        .useStoryParser(new RegexStoryParser(examplesTableFactory))
        .useStoryReporterBuilder(new StoryReporterBuilder()
            .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass)).withDefaultFormats()
            .withFormats(CONSOLE, TXT, HTML, XML))
        .useParameterConverters(parameterConverters);
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    return new InstanceStepsFactory(configuration(), new MySteps());
  }

  @Override
  protected List<String> storyPaths() {
    return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
  }
}