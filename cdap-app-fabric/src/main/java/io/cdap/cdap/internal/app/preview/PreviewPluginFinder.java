/*
 * Copyright © 2022 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.cdap.internal.app.preview;

import com.google.inject.Inject;
import io.cdap.cdap.common.ArtifactNotFoundException;
import io.cdap.cdap.common.internal.remote.RemoteClientFactory;
import io.cdap.cdap.common.io.Locations;
import io.cdap.cdap.internal.app.runtime.artifact.RemotePluginFinder;
import io.cdap.cdap.internal.app.worker.sidecar.ArtifactLocalizerClient;
import io.cdap.cdap.proto.id.ArtifactId;
import io.cdap.cdap.security.spi.authorization.UnauthorizedException;
import org.apache.twill.filesystem.Location;
import org.apache.twill.filesystem.LocationFactory;

import java.io.IOException;

/**
 * PreviewPluginFinder is an extension of {@link RemotePluginFinder} that is meant to be used exclusively in tasks
 * running in the {@link PreviewRunnerTwillRunnable}. This implementation uses the {@link ArtifactLocalizerClient} to
 * download and cache the given artifact on the local file system; however, it does not unpack the artifact as the
 * SparkCompiler may require it packed to function properly. See CDAP-19311 for details.
 */
public class PreviewPluginFinder extends RemotePluginFinder {
  private final ArtifactLocalizerClient artifactLocalizerClient;

  @Inject
  PreviewPluginFinder(LocationFactory locationFactory,
                      RemoteClientFactory remoteClientFactory,
                      ArtifactLocalizerClient artifactLocalizerClient) {
    super(locationFactory, remoteClientFactory);
    this.artifactLocalizerClient = artifactLocalizerClient;
  }

  @Override
  protected Location getArtifactLocation(ArtifactId artifactId)
    throws IOException, ArtifactNotFoundException, UnauthorizedException {
    return Locations.toLocation(artifactLocalizerClient.getArtifactLocation(artifactId));
  }
}
