/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.material.catalog.themeswitcher;

import io.material.catalog.R;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.ArrayRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import io.material.catalog.themeswitcher.ThemeAttributeValuesCreator.ThemeAttributeValues;
import io.material.catalog.windowpreferences.WindowPreferencesManager;
import javax.inject.Inject;

/**
 * Theme switcher dialog that allows the user to change different theming aspects like colors and
 * shapes. Each group in the dialog has a set of possible style values that are used as theme
 * overlays, overriding attributes in the base theme like shape appearances or color attributes.
 */
public class ThemeSwitcherDialogFragment extends BottomSheetDialogFragment
    implements HasAndroidInjector {

  private enum RadioButtonType {
    DEFAULT,
    XML,
  }

  private enum ThemingType {
    COLOR(RadioButtonType.DEFAULT),
    SHAPE_CORNER_FAMILY(RadioButtonType.XML),
    SHAPE_CORNER_SIZE(RadioButtonType.DEFAULT);

    private final RadioButtonType radioButtonType;

    ThemingType(RadioButtonType type) {
      radioButtonType = type;
    }
  }

  @Inject ThemeSwitcherResourceProvider resourceProvider;
  @Inject ThemeAttributeValuesCreator themeAttributeValuesCreator;


  private WindowPreferencesManager windowPreferencesManager;

  @Override
  public void onCreate(@Nullable Bundle bundle) {
    super.onCreate(bundle);
    windowPreferencesManager = new WindowPreferencesManager(getContext());
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    // We have to apply the edge to edge preference to the new window that is created.
    windowPreferencesManager.applyEdgeToEdgePreference(dialog.getWindow());
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater layoutInflater,
      @Nullable ViewGroup viewGroup,
      @Nullable Bundle bundle) {
    View view = layoutInflater.inflate(R.layout.mtrl_theme_switcher_dialog, viewGroup, false);
    initializeChooseThemeButtons(view);


    return view;
  }

  private void initializeChooseThemeButtons(View view) {
    Context context = view.getContext();
    ThemePreferencesManager themePreferencesManager =
        new ThemePreferencesManager(context, resourceProvider);

    MaterialButtonToggleGroup themeToggleGroup = view.findViewById(R.id.theme_toggle_group);
    themeToggleGroup.check(themePreferencesManager.getCurrentThemeId());

    themeToggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
      if (isChecked) {
        themePreferencesManager.saveAndApplyTheme(checkedId);
      }
    });
  }





  @NonNull
  private AppCompatRadioButton createCompatRadioButton(
      RadioGroup group, String contentDescription) {
    AppCompatRadioButton button = new AppCompatRadioButton(getContext());
    button.setContentDescription(contentDescription);
    group.addView(button);
    return button;
  }

  @Inject DispatchingAndroidInjector<Object> childFragmentInjector;

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    return childFragmentInjector;
  }
}
