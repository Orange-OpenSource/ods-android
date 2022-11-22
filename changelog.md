# ODS library changelog

All notable changes done in ODS library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased](https://github.com/Orange-OpenSource/ods-android/compare/0.7.0...master)

### Added

- \[Lib\] Add `OdsSnackbar` and `OdsSnackbarHost` composable to manage snackbars display ([#114](https://github.com/Orange-OpenSource/ods-android/issues/114))

### Changed

- \[All\] Version numbers in changelog now display changes on GitHub when clicked ([#322](https://github.com/Orange-OpenSource/ods-android/issues/322))
- \[All\] Update documentation [#334](https://github.com/Orange-OpenSource/ods-android/issues/334)
- \[Demo\] Add customization bottom sheets for buttons ([#303](https://github.com/Orange-OpenSource/ods-android/issues/303))
- \[Demo\] Replace action buttons switches by a counter in cards customization bottom sheet ([#327](https://github.com/Orange-OpenSource/ods-android/issues/327))
- \[Demo\] Add customization bottom sheets for sliders ([#307](https://github.com/Orange-OpenSource/ods-android/issues/307))
- \[Lib\] Allow to add side icons to `OdsSliderLockups` ([#307](https://github.com/Orange-OpenSource/ods-android/issues/307))
- \[Lib\] Use `OptIn` annotation instead of propagating `ExperimentalMaterialApi` and `ExperimentalPagerApi` ([#320](https://github.com/Orange-OpenSource/ods-android/issues/320))
- \[Lib\] Use multipreview annotation instead of duplicating `Preview` annotations ([#324](https://github.com/Orange-OpenSource/ods-android/issues/324))
- \[Lib\] Change `OdsIconToggleButton` display ([#303](https://github.com/Orange-OpenSource/ods-android/issues/303))

### Fixed

- \[Lib\] Fix leading icon tab display for Innovation Cup theme ([#330](https://github.com/Orange-OpenSource/ods-android/issues/330))

## [0.7.0](https://github.com/Orange-OpenSource/ods-android/compare/0.6.0...0.7.0) - 2022-11-09

### Added

- \[ThemeConfigurationContract\] Add module to create themes
- \[ThemeOrange\] Add module for Orange theme
- \[ThemeInnovationCup\] Add module for Innovation Cup theme

### Changed

- \[Demo\] Display an illustration and description header on guideline typography screen ([#301](https://github.com/Orange-OpenSource/ods-android/issues/301))
- \[Demo\] Use step counter for list item line count customization ([#306](https://github.com/Orange-OpenSource/ods-android/issues/306))
- \[Demo\] Display the chip name and a short description for each type of chip ([#304](https://github.com/Orange-OpenSource/ods-android/issues/304))

## [0.6.0](https://github.com/Orange-OpenSource/ods-android/compare/0.5.0...0.6.0) - 2022-10-17

### Added

- \[Demo\] Display composable name associated to each component or variant ([#267](https://github.com/Orange-OpenSource/ods-android/issues/267))

### Changed

- \[Demo\] Progress & activities components have been split in two variants ([#282](https://github.com/Orange-OpenSource/ods-android/issues/282))
- \[Demo\] Components detail screens have been homogenized ([#254](https://github.com/Orange-OpenSource/ods-android/issues/254))
- \[Demo\] Variants displayed in buttons detail screen have been changed ([#260](https://github.com/Orange-OpenSource/ods-android/issues/260), [#278](https://github.com/Orange-OpenSource/ods-android/issues/278))
- \[Demo\] Expand components customization bottom sheets by default ([#284](https://github.com/Orange-OpenSource/ods-android/issues/284))
- \[Demo\] Color hex value can now be copied to the clipboard ([#256](https://github.com/Orange-OpenSource/ods-android/issues/256))
- \[Lib\] Cards and buttons composables have been renamed to match with Jetpack compose SDK naming ([#267](https://github.com/Orange-OpenSource/ods-android/issues/267))
- \[Lib\] `OdsButtonContainedStyle` has been renamed into `OdsButtonStyle` ([#260](https://github.com/Orange-OpenSource/ods-android/issues/260))
- \[Lib\] Rename `ChoiceChip` to `OdsChoiceChip` ([#271](https://github.com/Orange-OpenSource/ods-android/issues/271))

### Fixed

- \[Demo\] Fix state vocalization for customization bottom sheets ([#279](https://github.com/Orange-OpenSource/ods-android/issues/279))
- \[Demo\] Fix vocalization of counters values on change ([#266](https://github.com/Orange-OpenSource/ods-android/issues/266))
- \[Lib\] Fix state vocalization for chips: selected/not selected ([#280](https://github.com/Orange-OpenSource/ods-android/issues/280))

## [0.5.0](https://github.com/Orange-OpenSource/ods-android/compare/0.4.0...0.5.0) - 2022-09-07

### Added

- \[Demo\] Allow to enable or disable click on cards in customization bottom sheet ([#247](https://github.com/Orange-OpenSource/ods-android/issues/247))
- \[Demo\] Add spacing guideline screen ([#211](https://github.com/Orange-OpenSource/ods-android/issues/211))
- \[Demo\] Add customizable top app bar regular component preview ([#105](https://github.com/Orange-OpenSource/ods-android/issues/105))
- \[Lib\] Add `KeyboardState` utility composable which allows to know if the keyboard is opened or closed in Jetpack Compose applications ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsTextFieldCounter`, a composable to add a counter below a text field ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsPasswordTextField` and `OdsPasswordOutlinedTextField` which are text fields implementations for password entry ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsChoiceChipsFlowRow`, a composable to display a set of choice chips in a full width flow row ([#169](https://github.com/Orange-OpenSource/ods-android/issues/169))
- \[Lib\] Add `imageBackgroundColor`, `imageContentScale` and `imageAlignment` properties to `OdsCardImageFirst`, `OdsCardSmall` and `OdsCardTitleFirst` ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[Lib\] Add `OdsListItemScope` and `OdsListItemIconType` enum as well as `Modifier.iconType` and `Modifier.divider` methods to configure `OdsListItem` icon type and divider ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

### Changed

- \[Demo\] Customization radio buttons has been replaced by chips ([#169](https://github.com/Orange-OpenSource/ods-android/issues/169))
- \[Demo\] The number of items in bottom navigation can now be changed using plus and minus buttons ([#172](https://github.com/Orange-OpenSource/ods-android/issues/172))
- \[Demo\] Update lists component UI ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))
- \[Lib\] Replace `@DrawableRes Int` parameters type by `Painter` for all cards and buttons components ([#262](https://github.com/Orange-OpenSource/ods-android/issues/262))
- \[Lib\] Change `OdsSlider` signature: `leftIconRes` and `rightIconRes` have been replaced respectively by `leftIcon` and `rightIcon` which are `Painter` typed ([#243](https://github.com/Orange-OpenSource/ods-android/issues/243))
- \[Lib\] Change `OdsTopAppBar` signature: `title` parameter type changed to String and `onNavigationIconClick` parameter has been added ([#105](https://github.com/Orange-OpenSource/ods-android/issues/105))

### Fixed

- \[Demo\] Fix vocalization of icons for sliders with icons ([#243](https://github.com/Orange-OpenSource/ods-android/issues/243))
- \[Demo\] Fix list item number of lines wording ([#252](https://github.com/Orange-OpenSource/ods-android/issues/252))
- \[Demo\] Fix a bug where guideline and component images were cropped ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[Demo\] Fix a bug where back button did not collapse expanded bottom sheets ([#238](https://github.com/Orange-OpenSource/ods-android/issues/238))
- \[Lib\] Cards are no more always clickable and corner radius has been added to the ripple effect ([#247](https://github.com/Orange-OpenSource/ods-android/issues/247))
- \[Lib\] Fix a crash when last tab is selected and the user decreases tabs number ([#245](https://github.com/Orange-OpenSource/ods-android/issues/245))
- \[Lib\] Fix ripple effect colors for outlined and text buttons when forced on dark or light ([#168](https://github.com/Orange-OpenSource/ods-android/issues/168))

### Removed

- \[Lib\] Remove `OdsListItemWideThumbnail`, `OdsListWideThumbnail` and `OdsListSquaredThumbnail` methods ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

## [0.4.0](https://github.com/Orange-OpenSource/ods-android/compare/0.0.1...0.4.0) - 2022-07-06

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
