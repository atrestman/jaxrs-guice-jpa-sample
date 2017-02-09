package io.swagger.common;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/2/17.
 */
public abstract class BaseGuiceModule extends ServletModule {

    private
    final Logger logger = Logger.getLogger(BaseGuiceModule.class.getName());

    protected Properties properties;

    /**
     * @return The fully resolved Properties collection that resulted from
     *         merging the file/resource paths passed into the constructor.
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Loads properties from one or more properties files and binds them for use
     * in @Named.
     *
     * @param propertiesResourcePaths
     *        The properties file resource paths to load. The resource paths
     *        listed later take precedence.
     * @param propertiesFilePaths
     *        The properties file paths to load. These properties override
     *        resource path properties files. The properties files listed later
     *        take precedence.
     */
    protected void loadProperties(
            String[] propertiesResourcePaths,
            String[] propertiesFilePaths) {
        super.configureServlets();

        // Load properties
        Properties allProperties = new Properties();
        try {

            // Load properties from the resource paths.
            int resourcesLoaded = 0;
            for (String propertiesPath : propertiesResourcePaths) {
                InputStream stream = null;
                try {
                    stream = BaseGuiceModule.class.getResourceAsStream(propertiesPath);
                    this.overlayProperties(stream, allProperties);
                    resourcesLoaded++;
                }
                catch (Exception x) {
                    logger.log(Level.INFO, "Failed to load properties resource: " + propertiesPath, x);
                }
            }
            logger.log(Level.INFO, "Loaded {0} properties resources.", resourcesLoaded);

            // Load properties from the file paths.
            int filesLoaded = 0;
            for (String propertiesPath : propertiesFilePaths) {
                File file = new File(propertiesPath);
                try {
                    if (file.exists()) {
                        InputStream stream = null;
                        try {
                            stream = new FileInputStream(propertiesPath);
                            this.overlayProperties(stream, allProperties);
                            filesLoaded++;
                        }
                        catch (Exception x) {
                            logger.log(Level.WARNING, "Properties file not found: {0}", propertiesPath);
                        }
                    }
                    else {
                        logger.log(Level.WARNING, "Properties file not found: {0}", propertiesPath);
                    }
                }
                catch (Exception e) {
                    logger.log(
                            Level.WARNING,
                            "Properties file lookup failed: {0} with error: {1}",
                            new Object[]{
                                    propertiesPath,
                                    e.getMessage()
                            });
                }

            }
            logger.log(Level.INFO, "Loaded {0} properties files.", filesLoaded);

            // Sort and log the properties read in at startup.
            TreeMap<String, String> sortedProperties = new TreeMap<String, String>();
            for (Object key : allProperties.keySet()) {
                sortedProperties.put(key.toString(), allProperties.getProperty(key.toString()));
            }
            logger.log(Level.INFO, "PROPERTIES LOADED: ");
            for (String key : sortedProperties.keySet()) {
                logger.log(Level.FINE, " [{0}] ", key);
            }

            // Bind those properties to @Named
            Names.bindProperties(binder(), allProperties);

            // Save a reference to the properties file.
            this.properties = allProperties;
        }
        catch (Exception x) {
            logger.log(Level.SEVERE, x.getMessage());
        }

    }

    /**
     * Load properties from the given stream and insert them into the Properties
     * object passed in. This may overwrite some existing properties.
     *
     * @param stream
     *        The stream containing Properties.
     * @param into
     *        The Properties to overlay into.
     * @throws Exception
     */
    private void overlayProperties(InputStream stream, Properties into) throws Exception {
        if (stream != null) {
            Properties overlay = new Properties();
            overlay.load(stream);
            this.overlayProperties(overlay, into);
        }
    }

    /**
     * Load properties from the given Properties and insert them into the other
     * Properties object passed in. This may overwrite some existing properties.
     *
     * @param overlay
     *        The Properties to overlay
     * @param into
     *        The Properties to overlay into
     */
    private void overlayProperties(Properties overlay, Properties into) {
        for (Object name : overlay.keySet()) {
            String propertyName = (String) name;
            if (into.containsKey(propertyName)) {
                into.remove(propertyName);
            }
            into.put(propertyName, overlay.get(propertyName));
        }
    }

    public List<String> getPropertyNamesWith(String prefix, String suffix) {
        ArrayList<String> propNames = new ArrayList<String>();
        Set<Object> propertyNames = this.properties.keySet();
        for (Object obj : propertyNames) {
            String propertyName = (String) obj;
            if (
                // No prefix given or prefix given and it matches
                    (prefix == null || (prefix != null && propertyName.startsWith(prefix)))
                            &&
                            // No suffix given or suffix given and it matches
                            (suffix == null || (suffix != null && propertyName.endsWith(suffix)))

                    ) {
                propNames.add(propertyName);
            }
        }
        return propNames;
    }

    /**
     * @return
     */
    public Map<String, String> getPropertyMap(String prefix) {
        Map<String, String> propertyMap = new HashMap<String, String>();
        List<String> propertyKeys = this.getPropertyNamesWith(prefix, null);
        for (String propertyKey : propertyKeys) {
            String left = propertyKey.substring(propertyKey.lastIndexOf(".") + 1).trim();
            String right = this.getProperties().getProperty(propertyKey);
            propertyMap.put(left, right);
        }
        return propertyMap;
    }

    /**
     * @return
     */
    public Map<String, List<String>> getPropertyListMap(String prefix) {
        return this.getPropertyListMap(prefix, false);
    }

    public Map<String, List<String>> getPropertyListMap(String prefix, boolean invert) {
        Map<String, List<String>> propertyListMap = new HashMap<String, List<String>>();
        Map<String, String> propertyMap = this.getPropertyMap(prefix);
        for (String propertyMapKey : propertyMap.keySet()) {
            if (!propertyListMap.containsKey(propertyMapKey)) {
                propertyListMap.put(propertyMapKey, new ArrayList<String>());
            }
            List<String> propertyListValues = propertyListMap.get(propertyMapKey);
            String propertyMapValue = propertyMap.get(propertyMapKey);
            String[] values = propertyMapValue.split(",");
            for (String value : values) {
                if (value != null && value.length() > 0) {
                    propertyListValues.add(value);
                }
            }
        }
        if (invert) {
            Map<String, List<String>> inverted = new HashMap<String, List<String>>();
            for (String role : propertyListMap.keySet()) {
                List<String> emails = propertyListMap.get(role);
                for (String email : emails) {
                    if (!inverted.containsKey(email)) {
                        inverted.put(email, new ArrayList<String>());
                    }
                    List<String> roles = inverted.get(email);
                    if (!roles.contains(role)) {
                        roles.add(role);
                    }
                    logger.log(
                            Level.INFO,
                            "Mapping e-mail address \"{0}\" to roles \"{1}\".)",
                            new Object[]{email, roles});
                }
            }
            propertyListMap = inverted;

        }
        return propertyListMap;
    }
}
