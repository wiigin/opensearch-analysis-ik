package org.opensearch.index.analysis;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IkAnalyzerProvider extends AbstractIndexAnalyzerProvider<IKAnalyzer> {
    private final IKAnalyzer analyzer;

    public IkAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings,boolean useSmart) {
        super(indexSettings, name, settings);

        Configuration configuration=new Configuration(env,settings).setUseSmart(useSmart);

        analyzer=new IKAnalyzer(configuration);
    }

    public static IkAnalyzerProvider getIkSmartAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new IkAnalyzerProvider(indexSettings,env,name,settings,true);
    }

    public static IkAnalyzerProvider getIkAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new IkAnalyzerProvider(indexSettings,env,name,settings,false);
    }

    @Override public IKAnalyzer get() {
        return this.analyzer;
    }
}
