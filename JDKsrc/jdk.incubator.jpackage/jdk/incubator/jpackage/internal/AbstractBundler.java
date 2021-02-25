/*
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.incubator.jpackage.internal;

import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * AbstractBundler
 *
 * This is the base class all bundlers extend from.
 * It contains methods and parameters common to all bundlers.
 * The concrete implementations are in the platform specific bundlers.
 */
abstract class AbstractBundler implements Bundler {

    static final BundlerParamInfo<File> IMAGES_ROOT =
            new StandardBundlerParam<>(
            "imagesRoot",
            File.class,
            params -> new File(
                StandardBundlerParam.TEMP_ROOT.fetchFrom(params), "images"),
            (s, p) -> null);

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void cleanup(Map<String, ? super Object> params) {
        try {
            IOUtils.deleteRecursive(
                    StandardBundlerParam.TEMP_ROOT.fetchFrom(params));
        } catch (IOException e) {
            Log.verbose(e.getMessage());
        }
    }
}
