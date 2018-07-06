package org.blimpit.external.verger.inventory.config;

import org.blimpit.external.verger.inventory.model.Feature;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ApplicationProperties {

    private static ApplicationProperties applicationProperties;
    private Properties properties = new Properties();
    private List<Feature> featureList = new ArrayList();

    private ApplicationProperties(String filePath) {
        InputStream input = null;

        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            this.properties.load(input);
            Iterator var3 = this.properties.keySet().iterator();

            while(var3.hasNext()) {
                Object key = var3.next();
                if (key instanceof String && ((String)key).startsWith("feature")) {
                    Feature feature = new Feature();
                    String name = (String)key;
                    feature.setFeatureId(name);
                    feature.setFeatureName(this.properties.getProperty((String)key));
                    this.featureList.add(feature);
                }
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }

    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

    public Feature[] getFeatures() {
        return (Feature[])this.featureList.toArray(new Feature[this.featureList.size()]);
    }

    public static ApplicationProperties getInstance(String filePath) {
        if (applicationProperties == null) {
            Class var1 = ApplicationProperties.class;
            synchronized(ApplicationProperties.class) {
                if (applicationProperties == null) {
                    applicationProperties = new ApplicationProperties(filePath);
                }
            }
        }

        return applicationProperties;
    }
}
