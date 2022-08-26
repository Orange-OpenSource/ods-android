# ODS library changelog

All notable changes done in ODS library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

### Added

- \[Demo\] Add spacing guideline screen ([#211](https://github.com/Orange-OpenSource/ods-android/issues/211))
- \[Lib\] Add `imageBackgroundColor`, `imageContentScale` and `imageAlignment` properties to `OdsCardImageFirst`, `OdsCardSmall` and `OdsCardTitleFirst` ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[Lib\] Add `OdsListItemScope` and `OdsListItemIconType` enum as well as `Modifier.iconType` and `Modifier.divider` methods to configure `OdsListItem` icon type and divider ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

### Changed

- \[Demo\] The number of items in bottom navigation can now be changed using plus and minus buttons ([#172](https://github.com/Orange-OpenSource/ods-android/issues/172))
- \[Demo\] Update lists component UI ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

### Fixed

- \[Demo\] Fix list item number of lines wording ([#252](https://github.com/Orange-OpenSource/ods-android/issues/252))
- \[Demo\] Fix a bug where guideline and component images were cropped ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[Demo\] Fix a bug where back button did not collapse expanded bottom sheets ([#238](https://github.com/Orange-OpenSource/ods-android/issues/238))
- \[Lib\] Fix a crash when last tab is selected and the user decreases tabs number ([#245](https://github.com/Orange-OpenSource/ods-android/issues/245))

### Removed

- \[Lib\] Remove `OdsListItemWideThumbnail`, `OdsListWideThumbnail` and `OdsListSquaredThumbnail` methods ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

## 0.4.0 - 2022-07-06

### Added

- \[Lib\] Add chips components: `OdsChip` and `OdsFilterChip` ([#113](https://github.com/Orange-OpenSource/ods-android/issues/113))
- \[Lib\] Add tabs components: `OdsLeadingIconTab`, `OdsTab`, `OdsTabRow` and `OdsScrollableTabRow` ([#108](https://github.com/Orange-OpenSource/ods-android/issues/108))
- \[Lib\] Add text fields components: `OdsTextField` and `OdsOutlinedTextField` ([#83](https://github.com/Orange-OpenSource/ods-android/issues/83))
- \[Lib\] Add `OdsAlertDialog` component ([#101](https://github.com/Orange-OpenSource/ods-android/issues/101))
- \[Lib\] Add list items components: `OdsListItem` and `OdsListItemWideThumbnail` ([#73](https://github.com/Orange-OpenSource/ods-android/issues/73))
- \[Lib\] Add controls components: `OdsCheckbox`, `OdsRadioButton`, `OdsSwitch`, `OdsSlider` ([#64](https://github.com/Orange-OpenSource/ods-android/issues/64))
- \[Lib\] Add buttons components: `OdsButton`, `OdsOutlinedButton`, `OdsTextButton` and `OdsToggleButton` ([#45](https://github.com/Orange-OpenSource/ods-android/issues/45))
- \[Lib\] Add `OdsTopAppBar` component ([#25](https://github.com/Orange-OpenSource/ods-android/issues/25))
- \[Lib\] Add `OdsCardSmall`, `OdsCardImageFirst` and `OdsCardTitleFirst` components ([#32](https://github.com/Orange-OpenSource/ods-android/issues/32), [#33](https://github.com/Orange-OpenSource/ods-android/issues/33), [#34](https://github.com/Orange-OpenSource/ods-android/issues/34))
- \[Lib\] Add `OdsBottomNavigation` and `OdsBottomNavigationItem` components ([#20](https://github.com/Orange-OpenSource/ods-android/issues/20))
- \[Lib\] Add `OdsMaterialTheme` which is a Material theme extension for Jetpack Compose applications ([#36](https://github.com/Orange-OpenSource/ods-android/issues/36))
- \[Lib\] Add `Theme.Orange` and `Theme.Orange.NoActionBar`, the Orange Design System theme for XML applications

\[Unreleased\]: <https://github.com/Orange-OpenSource/ods-android/compare/0.4.0...master>
\[0.4.0\]: <https://github.com/Orange-OpenSource/ods-android/compare/0.0.1...0.4.0>
\[0.0.1\]: <https://github.com/Orange-OpenSource/ods-android/tree/0.0.1>
