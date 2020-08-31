/*
 * Copyright (C) 2019 Validus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tipsy.martysplace.fragments.system;

import com.android.internal.logging.nano.MetricsProto;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;

import com.android.internal.util.gzosp.DeviceUtils;
import com.android.settings.R;

import com.android.settings.tipsy.SystemSettingSwitchPreference;

import com.android.settings.SettingsPreferenceFragment;

public class PocketMode extends SettingsPreferenceFragment {

    private static final String SYSTEM_PROXI_CHECK_ENABLED = "system_proxi_check_enabled";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.pocket_mode);

        boolean supportPowerButtonProxyCheck = getResources().getBoolean(com.android.internal.R.bool.config_proxiSensorWakupCheck);
        SystemSettingSwitchPreference proxyCheckPreference = (SystemSettingSwitchPreference) findPreference(SYSTEM_PROXI_CHECK_ENABLED);
        if (!DeviceUtils.deviceSupportsProximitySensor(getActivity()) || !supportPowerButtonProxyCheck) {
            getPreferenceScreen().removePreference(proxyCheckPreference);
       }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.TIPSY;
    }
}
