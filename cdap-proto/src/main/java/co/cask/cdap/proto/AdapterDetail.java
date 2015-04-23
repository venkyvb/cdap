/*
 * Copyright © 2015 Cask Data, Inc.
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

package co.cask.cdap.proto;

import co.cask.cdap.api.schedule.ScheduleSpecification;
import com.google.gson.JsonElement;

/**
 * Represents an adapter returned for /adapters/{adapter-id}.
 */
public class AdapterDetail {
  private final String name;
  private final String description;
  private final String template;
  private final Id.Program program;
  private final JsonElement config;
  private final ScheduleSpecification schedule;

  public AdapterDetail(String name, String description, String template, Id.Program program,
                       JsonElement config, ScheduleSpecification schedule) {
    this.name = name;
    this.description = description;
    this.template = template;
    this.program = program;
    this.config = config;
    this.schedule = schedule;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getTemplate() {
    return template;
  }

  public Id.Program getProgram() {
    return program;
  }

  public JsonElement getConfig() {
    return config;
  }

  public ScheduleSpecification getSchedule() {
    return schedule;
  }
}
