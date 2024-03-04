# ODS library changelog

All notable changes done in ODS library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased](https://github.com/Orange-OpenSource/ods-android/compare/0.18.0...develop)

### Added

- \[App\] Display third party libraries in the About section ([#485](https://github.com/Orange-OpenSource/ods-android/issues/485))
- \[App\] Display XML code implementation for components with an XML version available ([#788](https://github.com/Orange-OpenSource/ods-android/issues/788))
- \[App\] Display Compose code implementation for `OdsTabRow` and `OdsScrollableTabRow` ([#549](https://github.com/Orange-OpenSource/ods-android/issues/549))
- \[Doc\] Add XML integration documentation for components with an XML version ([#540](https://github.com/Orange-OpenSource/ods-android/issues/540))
- \[Lib\] Add `OdsSearchTopAppBar` component variant ([#577](https://github.com/Orange-OpenSource/ods-android/issues/577))
- \[Lib\] Add `OdsEmptyStateView` module ([#119](https://github.com/Orange-OpenSource/ods-android/issues/119))

### Changed

- \[App\] Display typography tokens in search results ([#551](https://github.com/Orange-OpenSource/ods-android/issues/551))
- \[App\] Update checkboxes and switches components demo by showing only one element ([#666](https://github.com/Orange-OpenSource/ods-android/issues/666))
- \[App\] Replace `OdsListItem` lines counter customization by choice chips allowing secondary text lines count customization ([#799](https://github.com/Orange-OpenSource/ods-android/issues/799))
- \[Doc\] Update About module integration instructions ([#815](https://github.com/Orange-OpenSource/ods-android/issues/815))
- \[Doc\] Update colors and typography documentations ([#818](https://github.com/Orange-OpenSource/ods-android/issues/818))
- \[Lib\] Update i18n keys ([#752](https://github.com/Orange-OpenSource/ods-android/issues/752))
- \[Lib\] Update `OdsChoiceChipsFlowRow` to have less vertical space between elements ([#758](https://github.com/Orange-OpenSource/ods-android/issues/758))
- \[Lib\] Change `OdsListItem` API by replacing `singleLineSecondaryText` boolean parameter by an enum called `secondaryTextLineCount` ([#777](https://github.com/Orange-OpenSource/ods-android/issues/777))
- \[Lib\] Update typography tokens and rename ODS text composables ([#615](https://github.com/Orange-OpenSource/ods-android/issues/615))
- \[Lib\] Move placeholder images from `app` to `lib` ([#531](https://github.com/Orange-OpenSource/ods-android/issues/531))
- \[Lib\] Stack cards buttons when texts are too long to fit on one line ([#620](https://github.com/Orange-OpenSource/ods-android/issues/620))
- \[Lib\] Stack `OdsBanner` buttons when texts are too long to fit on one line ([#795](https://github.com/Orange-OpenSource/ods-android/issues/795))
- \[Lib\] Update `OdsText` APIs and use `OdsText` instead of `Text` whenever possible ([#783](https://github.com/Orange-OpenSource/ods-android/issues/783))
- \[Lib\] Update `OdsChoiceChipsFlowRow` API ([#794](https://github.com/Orange-OpenSource/ods-android/issues/794))
- \[Lib\] Update `OdsIconToggleButtonsRow` and `OdsTextToggleButtonsRow` APIs ([#801](https://github.com/Orange-OpenSource/ods-android/issues/801))
- \[Lib\] Update `OdsChoiceChipsFlowRow` API ([#794](https://github.com/Orange-OpenSource/ods-android/issues/794))
- \[Lib\] Update `OdsButton`, `OdsTextButton`, `OdsOutlinedButton`, `OdsIconButton`, `OdsIconToggleButton`, `OdsIconToggleButtonsRow` and `OdsTextToggleButtonsRow` APIs by removing `displaySurface` parameters ([#830](https://github.com/Orange-OpenSource/ods-android/issues/830))
- \[LibXml\] Replace `displaySurface` attribute by `inverseTheme` for `OdsButton`, `OdsTextButton`, `OdsOutlinedButton`, `OdsIconButton`, `OdsIconToggleButton` and `OdsIconToggleButtonsRow` ([#830](https://github.com/Orange-OpenSource/ods-android/issues/830))

### Fixed

- \[App\] Fix `OdsTopAppBar` and `OdsLargeTopAppBar` components customization through the bottom sheet ([#747](https://github.com/Orange-OpenSource/ods-android/issues/747))
- \[App\] Fix guideline functional colors display when switching light/dark mode ([#761](https://github.com/Orange-OpenSource/ods-android/issues/761))
- \[App\] Fix code implementation dropdown menu contrast for accessibility reason by always enabling it ([#749](https://github.com/Orange-OpenSource/ods-android/issues/749))
- \[App\] Fix a bug where placeholder and error placeholder images did not change when switching between light and dark mode ([#531](https://github.com/Orange-OpenSource/ods-android/issues/531))
- \[Doc\] Fix a Jekyll warning with 404.html file when releasing a new version ([#822](https://github.com/Orange-OpenSource/ods-android/issues/822))
- \[Doc\] Fix typo in files copyright ([#821](https://github.com/Orange-OpenSource/ods-android/issues/821))
- \[Doc\] Fix a bug where content of left menu in documentation was wrong for old versions ([#738](https://github.com/Orange-OpenSource/ods-android/issues/738))
- \[Lib\] Update `OdsListItem` so that if it has a control trailing (checkbox, switch or radio button), the action is handled by the row only ([#743](https://github.com/Orange-OpenSource/ods-android/issues/743))
- \[Lib\] Remove padding added before `OdsLinearProgressIndicator` text if there is no icon displayed ([#745](https://github.com/Orange-OpenSource/ods-android/issues/745))
- \[Lib\] Update `OdsTopAppBar` and `OdsLargeTopAppBar` so that TalkBack reads the title of the app bar first ([#275](https://github.com/Orange-OpenSource/ods-android/issues/275))
- \[Lib\] Fix an issue in `OdsExposedDropdownMenu` where menu was not accessible with keyboard navigation ([#750](https://github.com/Orange-OpenSource/ods-android/issues/750))
- \[Lib\] Fix an A11y issue on `OdsBottomNavigation` where the items were focused twice when navigating with a keyboard ([#784](https://github.com/Orange-OpenSource/ods-android/issues/784))
- \[Lib\] Fix XML buttons display when forced on dark or light ([#656](https://github.com/Orange-OpenSource/ods-android/issues/656))

## [0.18.0](https://github.com/Orange-OpenSource/ods-android/compare/0.17.0...0.18.0) - 2023-12-11

### Added

- \[App\] Display `OdsBottomNavigation` XML code implementation ([#671](https://github.com/Orange-OpenSource/ods-android/issues/671))

### Changed

- \[All\] Upgrade dependencies ([#712](https://github.com/Orange-OpenSource/ods-android/issues/712))
- \[App\] Change tabs customization to be closer to the API ([#699](https://github.com/Orange-OpenSource/ods-android/issues/699))
- \[Lib\] Rename `OdsImageTile` to `OdsImageItem` ([#683](https://github.com/Orange-OpenSource/ods-android/issues/683))
- \[Lib\] Update `OdsTab`, `OdsLeadingIconTab`, `OdsTabRow` and `OdsScrollableTabRow` APIs ([#675](https://github.com/Orange-OpenSource/ods-android/issues/675))
- \[Lib\] Update `OdsTextField`, `OdsPasswordTextField` and `OdsSearchTextField` APIs ([#676](https://github.com/Orange-OpenSource/ods-android/issues/676))
- \[Lib\] Update `OdsModalDrawer` API ([#677](https://github.com/Orange-OpenSource/ods-android/issues/677))
- \[Lib\] Update `OdsBottomSheetScaffold` documentation ([#678](https://github.com/Orange-OpenSource/ods-android/issues/678))
- \[Lib\] Update `OdsChip` API to use a unique leading parameter ([#705](https://github.com/Orange-OpenSource/ods-android/issues/705))
- \[Lib\] Update most API types to be clearer for v1 launch ([#701](https://github.com/Orange-OpenSource/ods-android/issues/701))
- \[Lib\] Update `OdsBanner` to allow banner without button and avoid text truncation in banner for accessibility purpose ([#643](https://github.com/Orange-OpenSource/ods-android/issues/643))
- \[Lib\] Update `OdsSmallCard` to show full title and subtitle when the card is not clickable for accessibility purpose ([#722](https://github.com/Orange-OpenSource/ods-android/issues/722))
- \[Lib\] Update `OdsBottomNavigation` to show a selected line below the selected navigation item for accessibility purpose ([#723](https://github.com/Orange-OpenSource/ods-android/issues/723))

### Fixed

- \[App\] Fix nested class names in code implementation ([#708](https://github.com/Orange-OpenSource/ods-android/issues/708))
- \[App\] Fix bad vocalization for top bar change theme button ([#715](https://github.com/Orange-OpenSource/ods-android/issues/715))
- \[App\] Add clearer vocalization on bottom sheet demo screen ([#442](https://github.com/Orange-OpenSource/ods-android/issues/442))
- \[Lib\] Fix back button color in `OdsLargeTopAppBar` ([#685](https://github.com/Orange-OpenSource/ods-android/issues/685))
- \[Lib\] Fix a bug where the modifier was not taken into account in `OdsPasswordTextField` ([#710](https://github.com/Orange-OpenSource/ods-android/issues/710))
- \[Lib\] Fix an accessibility issue in `OdsListItem` where items in a list were overlapping when using big font size ([#716](https://github.com/Orange-OpenSource/ods-android/issues/716))

## [0.17.0](https://github.com/Orange-OpenSource/ods-android/compare/0.16.0...0.17.0) - 2023-11-06

### Added

- \[LibXml\] Add `OdsBottomNavigation` view ([#526](https://github.com/Orange-OpenSource/ods-android/issues/526))

### Changed

- \[App\] Change top app bar management ([#651](https://github.com/Orange-OpenSource/ods-android/issues/651))
- \[Lib\] Update `OdsBottomNavigation`, `OdsChoiceChipsFlowRow`, `OdsSlider` and `OdsSliderLockups` APIs ([#661](https://github.com/Orange-OpenSource/ods-android/issues/661))
- \[Lib\] Review and update technical documentation ([#638](https://github.com/Orange-OpenSource/ods-android/issues/638))
- \[Lib\] Update `OdsListItem` API ([#650](https://github.com/Orange-OpenSource/ods-android/issues/650))
- \[Lib\] Update `OdsSnackbarHost` API ([#674](https://github.com/Orange-OpenSource/ods-android/issues/674))

### Fixed

- \[App\] Start over progress components animations each time the determinate chip is selected ([#616](https://github.com/Orange-OpenSource/ods-android/issues/616))
- \[App\] Change illustrations alignment for slider and text field components ([#618](https://github.com/Orange-OpenSource/ods-android/issues/618))
- \[Lib\] Fix a bug where chip was not properly selected in `OdsChoiceChipsFlowRow` ([#650](https://github.com/Orange-OpenSource/ods-android/issues/650))
- \[Lib\] Change text color of `OdsTextToggleButtonsRow` selected button for accessibility reason ([#559](https://github.com/Orange-OpenSource/ods-android/issues/559))
- \[Lib\] Fix vertical padding of buttons in `OdsTextToggleButtonsRow` ([#559](https://github.com/Orange-OpenSource/ods-android/issues/559))

## [0.16.0](https://github.com/Orange-OpenSource/ods-android/compare/0.15.1...0.16.0) - 2023-10-10

### Added

- \[Lib\] Add `OdsModalDrawer` component documentation ([#652](https://github.com/Orange-OpenSource/ods-android/issues/652))

### Changed

- \[App\] Change buttons name ([#647](https://github.com/Orange-OpenSource/ods-android/issues/647))
- \[Lib\] Update `OdsSlider` and `OdsSliderLockups` APIs ([#648](https://github.com/Orange-OpenSource/ods-android/issues/648))

## [0.15.1](https://github.com/Orange-OpenSource/ods-android/compare/0.15.0...0.15.1) - 2023-09-28

### Changed

- \[Lib\] Update `OdsHorizontalCard`, `OdsSmallCard`, `OdsVerticalHeaderFirstCard` and `OdsVerticalImageFirstCard` APIs ([#597](https://github.com/Orange-OpenSource/ods-android/issues/597))
- \[Lib\] Remove several dependencies to reduce consumer app size ([#623](https://github.com/Orange-OpenSource/ods-android/issues/623))

### Fixed

- \[Lib\] Fix a bug where `OdsDropdownMenu` was not dismissed on user click ([#617](https://github.com/Orange-OpenSource/ods-android/issues/617))
- \[Lib\] Fix disabled color of text and icon in `OdsDropdownMenu` ([#617](https://github.com/Orange-OpenSource/ods-android/issues/617))
- \[Lib\] Fix background color of components in dark mode ([#634](https://github.com/Orange-OpenSource/ods-android/issues/634))
- \[Lib\] Fix a bug where the modifier was not taken into account in `OdsOutlinedTextField` ([#635](https://github.com/Orange-OpenSource/ods-android/issues/635))

## [0.15.0](https://github.com/Orange-OpenSource/ods-android/compare/0.14.0...0.15.0) - 2023-09-12

### Added

- \[App\] Add list item component ([#555](https://github.com/Orange-OpenSource/ods-android/issues/555))
- \[App\] Add toggle text buttons group component ([#530](https://github.com/Orange-OpenSource/ods-android/issues/530))
- \[App\] Add new convenience methods for code implementation ([#624](https://github.com/Orange-OpenSource/ods-android/issues/624))
- \[Lib\] Add `OdsTextToggleButtonsRow` component ([#530](https://github.com/Orange-OpenSource/ods-android/issues/530))

### Changed

- \[All\] Use Compose BOM and update Kotlin and Gradle versions ([#578](https://github.com/Orange-OpenSource/ods-android/issues/578))
- \[Lib\] Replace composable parameter of `OdsDropdownMenu` with a list of `OdsDropdownMenuItem` ([#572](https://github.com/Orange-OpenSource/ods-android/issues/572))
- \[Lib\] Update `OdsTopAppBar` and `OdsLargeTopAppBar` APIs ([#572](https://github.com/Orange-OpenSource/ods-android/issues/572))
- \[Lib\] Update `OdsBottomNavigation` API ([#588](https://github.com/Orange-OpenSource/ods-android/issues/588))
- \[Lib\] Update `OdsBanner` API ([#592](https://github.com/Orange-OpenSource/ods-android/issues/592))
- \[Lib\] Update `OdsButton`, `OdsOutlinedButton`and `OdsTextButton` APIs ([#596](https://github.com/Orange-OpenSource/ods-android/issues/596))
- \[Lib\] Update `OdsIconButton`, `OdsIconToggleButton`and `OdsIconToggleButtonsRow` APIs ([#599](https://github.com/Orange-OpenSource/ods-android/issues/599))
- \[Lib\] Update `OdsAlertDialog` API ([#605](https://github.com/Orange-OpenSource/ods-android/issues/605))
- \[Lib\] Update `OdsFloatingActionButton` and `OdsExtendedFloatingActionButton` APIs ([#611](https://github.com/Orange-OpenSource/ods-android/issues/611))
- \[Lib\] Update `OdsLinearProgressIndicator` and `OdsCircularProgressIndicator` APIs ([#607](https://github.com/Orange-OpenSource/ods-android/issues/607))
- \[Lib\] Update `OdsImageItem` API and rename it into `OdsImageTile` ([#609](https://github.com/Orange-OpenSource/ods-android/issues/609))
- \[Lib\] Update `OdsChip`, `OdsChoiceChipsFlowRow`and `OdsFilterChip` APIs ([#603](https://github.com/Orange-OpenSource/ods-android/issues/603))
- \[ThemeInnovationCup/ThemeOrange\] Remove Hilt dependency in themes ([#791](https://github.com/Orange-OpenSource/ods-android/issues/791))

### Fixed

- \[App\] Screen displayed on filter chip variant click was not the good one ([#580](https://github.com/Orange-OpenSource/ods-android/issues/580))
- \[App\] Fix a bug where `OdsListItem` text color did not update when switching between light and dark modes in text field demo ([#578](https://github.com/Orange-OpenSource/ods-android/issues/578))
- \[App\] The number of search results is now vocalized ([#506](https://github.com/Orange-OpenSource/ods-android/issues/506))
- \[Lib\] Vocalize exposed dropdown menu content as a group ([#560](https://github.com/Orange-OpenSource/ods-android/issues/560))
- \[Lib\] Fix `OdsTextField` content color in dark mode ([#621](https://github.com/Orange-OpenSource/ods-android/issues/621))

## [0.14.0](https://github.com/Orange-OpenSource/ods-android/compare/0.13.0...0.14.0) - 2023-07-12

### Added

- \[App\] Add component image item ([#473](https://github.com/Orange-OpenSource/ods-android/issues/473))
- \[App\] Display XML version in `ButtonsContained` and `ButtonsOutlined` ([#528](https://github.com/Orange-OpenSource/ods-android/issues/528))
- \[App\] Display XML version in `ButtonsText`, `ButtonsIcon`, `ButtonsIconToggle` and `ButtonsIconToggleGroup` ([#529](https://github.com/Orange-OpenSource/ods-android/issues/529))
- \[Lib\] Add `OdsLargeTopAppBar` component ([#118](https://github.com/Orange-OpenSource/ods-android/issues/118))
- \[Lib\] Add `OdsImageItem` component ([#473](https://github.com/Orange-OpenSource/ods-android/issues/473))
- \[Lib\] Add `@Parcelize` annotation on `OdsExposedDropdownMenuItem` to allow to save and restore it ([#545](https://github.com/Orange-OpenSource/ods-android/issues/545))
- \[Lib\] Add `themeColors` and `rippleTheme` properties to `OdsDisplaySurface` ([#329](https://github.com/Orange-OpenSource/ods-android/issues/329))
- \[LibXml\] Add `OdsButton` and `OdsOutlinedButton` views ([#528](https://github.com/Orange-OpenSource/ods-android/issues/528))
- \[LibXml\] Add `OdsTextButton`, `OdsIconButton`, `OdsIconToggleButton` and `OdsIconToggleButtonsRow` views ([#529](https://github.com/Orange-OpenSource/ods-android/issues/529))

### Changed

- \[App\] Use an exposed dropdown menu to switch between XML and Compose implementation ([#546](https://github.com/Orange-OpenSource/ods-android/issues/546))
- \[App\] Toggle icon buttons group can now be disabled with the customization bottom sheet ([#329](https://github.com/Orange-OpenSource/ods-android/issues/329))

### Fixed

- \[Lib\] Fix a bug where `enabled` property of `OdsIconToggleButtonsRowItem` has no effect ([#329](https://github.com/Orange-OpenSource/ods-android/issues/329))
- \[Lib\] Vocalize icon toggle button state change in `OdsIconToggleButtonsRow` component ([#567](https://github.com/Orange-OpenSource/ods-android/issues/567))

## [0.13.0](https://github.com/Orange-OpenSource/ods-android/compare/0.12.0...0.13.0) - 2023-06-01

### Added

- \[App\] Generalize code implementation section for all components ([#501](https://github.com/Orange-OpenSource/ods-android/issues/501))
- \[App\] Add a DSL for parameters in code implementation ([#520](https://github.com/Orange-OpenSource/ods-android/issues/520))
- \[App\] Display XML version of components in app when available ([#476](https://github.com/Orange-OpenSource/ods-android/issues/476))
- \[LibXml\] Add `lib-xml` module with `OdsBanner` view ([#476](https://github.com/Orange-OpenSource/ods-android/issues/476))

### Changed

- \[App\] Use Orange placeholder images ([#522](https://github.com/Orange-OpenSource/ods-android/issues/522))
- \[Lib\] Rename `OdsComponentApi` annotation and `OdsComponent` enum to `OdsComposable` ([#522](https://github.com/Orange-OpenSource/ods-android/issues/522))

### Fixed

- \[App\] Fix a bug where avatar have a default image ([#504](https://github.com/Orange-OpenSource/ods-android/issues/504))
- \[App\] Fix highest emphasis buttons background color ([#513](https://github.com/Orange-OpenSource/ods-android/issues/513))

## [0.12.0](https://github.com/Orange-OpenSource/ods-android/compare/0.11.1...0.12.0) - 2023-04-06

### Added

- \[App\] Add component search feature ([#16](https://github.com/Orange-OpenSource/ods-android/issues/16))
- \[App\] Add modal drawers component ([#112](https://github.com/Orange-OpenSource/ods-android/issues/112))
- \[Lib\] Add `OdsModalDrawer` component ([#112](https://github.com/Orange-OpenSource/ods-android/issues/112))
- \[Lib\] Add `OdsIconToggleButtonsRow` composable to display a group of toggle buttons ([#484](https://github.com/Orange-OpenSource/ods-android/issues/484))

### Changed

- \[App\] Update foods content
- \[App\] Rename application ID to `com.orange.ods.app` and replace `demo` with `app` in the project ([#469](https://github.com/Orange-OpenSource/ods-android/issues/469))
- \[App\] Update application name ([#474](https://github.com/Orange-OpenSource/ods-android/issues/474))
- \[App\] Remove copyright text in the about screen ([#486](https://github.com/Orange-OpenSource/ods-android/issues/486))
- \[App\] Update search with guidelines ([#478](https://github.com/Orange-OpenSource/ods-android/issues/478))
- \[App\] Update chips variants in order to have "Action chips", "Choice chips" and "Input chips" in the chips detail screen ([#494](https://github.com/Orange-OpenSource/ods-android/issues/494))
- \[App\] Split buttons and icons buttons in two different components ([#484](https://github.com/Orange-OpenSource/ods-android/issues/484))
- \[Lib\] Encapsulate a `MaterialTheme` in the `OdsTheme` to take directly benefits of colors set in Jetpack Compose SDK ([#430](https://github.com/Orange-OpenSource/ods-android/issues/430))
- \[Lib\] Update `OdsIconToggleButton` which displays a single icon button with two states. Use `OdsIconToggleButtonsRow` to display a group of toggle buttons ([#484](https://github.com/Orange-OpenSource/ods-android/issues/484))
- \[ThemeConfigurationContract\] Change colors contract by respectively grouping Material, functional and components colors in `materialColors`, `functionalColors` and `componentColors` properties of `OdsColors` ([#430](https://github.com/Orange-OpenSource/ods-android/issues/430))

### Fixed

- \[App\] Fix accessibility bug where content change on chip type selection was not read by TalkBack ([#332](https://github.com/Orange-OpenSource/ods-android/issues/332))
- \[App\] Fix accessibility bug where text fields error messages were not read by TalkBack on state change ([#359](https://github.com/Orange-OpenSource/ods-android/issues/359))
- \[App\] Fix crash on scrolling in guideline colors screen ([#477](https://github.com/Orange-OpenSource/ods-android/issues/477))
- \[App\] Fix a bug where the customization bottom sheet did not expand automatically in tabs component demo ([#458](https://github.com/Orange-OpenSource/ods-android/issues/458))
- \[Doc\] Fix a bug where documentation is displayed several times on the same page ([#457](https://github.com/Orange-OpenSource/ods-android/issues/457))
- \[Lib\] Implement workaround by adding content description to `OdsOutlinedTextField` in order to allow TalkBack to focus this type of text field ([#359](https://github.com/Orange-OpenSource/ods-android/issues/359))
- \[Lib\] Fix a bug where image height is wrong in some cases in `OdsHorizontalCard` ([#445](https://github.com/Orange-OpenSource/ods-android/issues/445))
- \[Lib\] Fix a bug where selected text in modal drawer is bold ([#502](https://github.com/Orange-OpenSource/ods-android/issues/502))

## [0.11.1](https://github.com/Orange-OpenSource/ods-android/compare/0.11.0...0.11.1) - 2023-03-10

### Fixed

- \[App\] Fix security vulnerability in the Manifest ([#463](https://github.com/Orange-OpenSource/ods-android/issues/463))

## [0.11.0](https://github.com/Orange-OpenSource/ods-android/compare/0.10.0...0.11.0) - 2023-03-03

### Added

- \[App\] Add banner component ([#115](https://github.com/Orange-OpenSource/ods-android/issues/115))
- \[App\] Add label for FAB size customization ([#413](https://github.com/Orange-OpenSource/ods-android/issues/413))
- \[App\] Add bottom sheet component ([#360](https://github.com/Orange-OpenSource/ods-android/issues/360))
- \[Lib\] Add `OdsBottomSheetScaffold` component ([#360](https://github.com/Orange-OpenSource/ods-android/issues/360))
- \[Lib\] Add `OdsHorizontalCard` component ([#35](https://github.com/Orange-OpenSource/ods-android/issues/35))
- \[Lib\] Add `OdsBanner` component ([#115](https://github.com/Orange-OpenSource/ods-android/issues/115))

### Changed

- \[Lib\] Add label, current value and icon parameters to `OdsLinearProgressIndicator` component to display text and/or icon above the indicator, and the current value below if necessary ([#362](https://github.com/Orange-OpenSource/ods-android/issues/362))
- \[Lib\] Add label parameter to `OdsCircularProgressIndicator` component to display a text below the indicator ([#362](https://github.com/Orange-OpenSource/ods-android/issues/362))
- \[Lib\] Rename `OdsTitleFirstCard` and `OdsImageFirstCard` respectively into `OdsVerticalHeaderFirstCard` and `OdsVerticalImageFirstCard` ([#432](https://github.com/Orange-OpenSource/ods-android/issues/432))

### Fixed

- \[App\] Update application name and description in about page ([#419](https://github.com/Orange-OpenSource/ods-android/issues/419))
- \[App\] Fix accessibility bug by preventing TalkBack to read the linear progress value twice ([#441](https://github.com/Orange-OpenSource/ods-android/issues/441))
- \[App\] Keep same bottom sheet height when content or not ([#447](https://github.com/Orange-OpenSource/ods-android/issues/447))

## [0.10.0](https://github.com/Orange-OpenSource/ods-android/compare/0.9.0...0.10.0) - 2023-02-07

### Added

- \[App\] Add new entries in about screen ([#403](https://github.com/Orange-OpenSource/ods-android/issues/403))
- \[Lib\] Add `OdsFloatingActionButton` and `OdsExtendedFloatingActionButton` components ([#109](https://github.com/Orange-OpenSource/ods-android/issues/109))
- \[Lib\] Add `OdsListItem` composable signature with an `OdsListItemTrailing` as trailing parameter in order to manage accessibility ([#387](https://github.com/Orange-OpenSource/ods-android/issues/387))
- \[Lib\] Add `OdsDropdownMenu` and `OdsExposedDropdownMenu` composables and related documentation ([#111](https://github.com/Orange-OpenSource/ods-android/issues/111))
- \[ThemeConfigurationContract\] Add `textFieldStyle` boolean in the theme contract to allow to choose between outlined or filled text fields in a custom theme ([#415](https://github.com/Orange-OpenSource/ods-android/issues/415))

### Changed

- \[All\] Upgrade dependencies ([#401](https://github.com/Orange-OpenSource/ods-android/issues/401))
- \[App\] Display food content ([#388](https://github.com/Orange-OpenSource/ods-android/issues/388))
- \[App\] Use `Enabled` wording in customization bottom sheets to be consistent with the code implementation ([#395](https://github.com/Orange-OpenSource/ods-android/issues/395))
- \[App\] Update structure of JSON file for recipes ([#385](https://github.com/Orange-OpenSource/ods-android/issues/385))
- \[Doc\] Update documentation home content for Jetpack Compose integration ([#409](https://github.com/Orange-OpenSource/ods-android/issues/409))
- \[ThemeConfigurationContract\] `OdsComponentCustomizations` has been renamed into `OdsComponentsConfiguration` ([#415](https://github.com/Orange-OpenSource/ods-android/issues/415))

### Fixed

- \[App\] In buttons screens, group lines of code into one vocalisation ([#392](https://github.com/Orange-OpenSource/ods-android/issues/392))
- \[App\] Fix an accessibility bug where icon buttons were not focusable with TalkBack ([#393](https://github.com/Orange-OpenSource/ods-android/issues/393))
- \[App\] Fix list item selection controls trailing vocalization: Checkboxes, Switches and Radio Buttons in lists ([#387](https://github.com/Orange-OpenSource/ods-android/issues/387))
- \[App\] Screens in about section now use current theme colors ([#390](https://github.com/Orange-OpenSource/ods-android/issues/390))
- \[App\] Fix crash when exiting the fixed tabs screen ([#418](https://github.com/Orange-OpenSource/ods-android/issues/418))
- \[Lib\] Fix cards vocalization when clickable ([#391](https://github.com/Orange-OpenSource/ods-android/issues/391))

### Removed

- \[All\] `OdsOutlinedTextfield` and `OdsPasswordOutlinedTextField` have been removed and replaced by `OdsTextField` and `OdsPasswordTextField`. Text fields appearance (outlined or filled) is now managed by the theme configuration. ([#415](https://github.com/Orange-OpenSource/ods-android/issues/415))

## [0.9.0](https://github.com/Orange-OpenSource/ods-android/compare/0.8.0...0.9.0) - 2023-01-06

### Added

- \[App\] Add `OdsIconButton` demo ([#294](https://github.com/Orange-OpenSource/ods-android/issues/294))
- \[Lib\] Add `OdsTopAppBarOverflowMenuBox` and `OdsDropdownMenu` composables to display an overflow menu in the top app bar ([#349](https://github.com/Orange-OpenSource/ods-android/issues/349))
- \[Lib\] Add `enabled` extension method on Compose `Color` ([#314](https://github.com/Orange-OpenSource/ods-android/issues/314))

### Changed

- \[App\] Change buttons screens by removing style customization and adding a code implementation section ([#339](https://github.com/Orange-OpenSource/ods-android/issues/339))
- \[App\] Display black and white illustrations for non Orange themes ([#386](https://github.com/Orange-OpenSource/ods-android/issues/386))
- \[Doc\] Use ODS Jekyll theme header to display favicons on documentation github pages ([#370](https://github.com/Orange-OpenSource/ods-android/issues/370))
- \[Lib\] Replace `tint` parameter of `OdsIconButton` composable methods by `displaySurface` ([#294](https://github.com/Orange-OpenSource/ods-android/issues/294))

### Fixed

- \[App\] Fix list item trailing icon vocalization and display ([#337](https://github.com/Orange-OpenSource/ods-android/issues/337))
- \[App\] Fix a bug where dark mode did not work properly in about screens on Android 13 ([#375](https://github.com/Orange-OpenSource/ods-android/issues/375))
- \[Lib\] Fix top app bar overflow menu colors ([#349](https://github.com/Orange-OpenSource/ods-android/issues/349))
- \[Lib\] `OdsSmallCard` title is now single line and truncated if needed ([#302](https://github.com/Orange-OpenSource/ods-android/issues/302))
- \[Lib\] Fix a bug where password was not hidden in `OdsTextField` and `OdsOutlinedTextField` ([#376](https://github.com/Orange-OpenSource/ods-android/issues/376))
- \[Lib\] Fix a bug where visualisation icon was still clickable in disabled state of `OdsTextField` and `OdsOutlinedTextField` ([#376](https://github.com/Orange-OpenSource/ods-android/issues/376))

## [0.8.0](https://github.com/Orange-OpenSource/ods-android/compare/0.7.0...0.8.0) - 2022-12-07

### Added

- \[All\] Add `NOTICE.txt` file ([#356](https://github.com/Orange-OpenSource/ods-android/issues/356))
- \[App\] Save the user theme selection in order to reopen the app with this theme [#335](https://github.com/Orange-OpenSource/ods-android/issues/335)
- \[App\] Add Snackbar component ([#114](https://github.com/Orange-OpenSource/ods-android/issues/114))
- \[App\] Display an error message below text fields if customization error switch is on ([#338](https://github.com/Orange-OpenSource/ods-android/issues/338))
- \[Lib\] Add `OdsSnackbar` and `OdsSnackbarHost` composable to manage snackbars display ([#114](https://github.com/Orange-OpenSource/ods-android/issues/114))
- \[Lib\] Add `errorMessage` parameter to `OdsTextField`, `OdsOutlinedTextField`, `OdsPasswordTextField` and `OdsPasswordOutlinedTextField` to allow the display of an error message below text fields ([#338](https://github.com/Orange-OpenSource/ods-android/issues/338))
- \[Lib\] Add `characterCounter` parameter to `OdsTextField`, `OdsOutlinedTextField`, `OdsPasswordTextField` and `OdsPasswordOutlinedTextField` to allow the display of a character counter below text fields ([#338](https://github.com/Orange-OpenSource/ods-android/issues/338))
- \[ThemeConfigurationContract\] Add `outlinedChips` boolean in the theme contract to allow to choose between outlined or filled chips in a custom theme ([#305](https://github.com/Orange-OpenSource/ods-android/issues/305))

### Changed

- \[All\] Version numbers in changelog now display changes on GitHub when clicked ([#322](https://github.com/Orange-OpenSource/ods-android/issues/322))
- \[All\] Update documentation [#334](https://github.com/Orange-OpenSource/ods-android/issues/334)
- \[All\] Upgrade compile and target SDK versions to 33 [#343](https://github.com/Orange-OpenSource/ods-android/issues/343)
- \[App\] Move change theme feature in top app bar by clicking on a palette icon [#335](https://github.com/Orange-OpenSource/ods-android/issues/335)
- \[App\] Add customization bottom sheets for buttons ([#303](https://github.com/Orange-OpenSource/ods-android/issues/303))
- \[App\] Replace action buttons switches by a counter in cards customization bottom sheet ([#327](https://github.com/Orange-OpenSource/ods-android/issues/327))
- \[App\] Add customization bottom sheets for sliders ([#307](https://github.com/Orange-OpenSource/ods-android/issues/307))
- \[App\] Demonstrate outlined or filled chips according theme configuration ([#305](https://github.com/Orange-OpenSource/ods-android/issues/305))
- \[Lib\] Allow to add side icons to `OdsSliderLockups` ([#307](https://github.com/Orange-OpenSource/ods-android/issues/307))
- \[Lib\] Use `OptIn` annotation instead of propagating `ExperimentalMaterialApi` and `ExperimentalPagerApi` ([#320](https://github.com/Orange-OpenSource/ods-android/issues/320))
- \[Lib\] Use multipreview annotation instead of duplicating `Preview` annotations ([#324](https://github.com/Orange-OpenSource/ods-android/issues/324))
- \[Lib\] Change `OdsIconToggleButton` display ([#303](https://github.com/Orange-OpenSource/ods-android/issues/303))
- \[ThemeInnovationCup\] Use filled chips ([#305](https://github.com/Orange-OpenSource/ods-android/issues/305))

### Fixed

- \[Lib\] Fix filter chips display for custom themes ([#305](https://github.com/Orange-OpenSource/ods-android/issues/305))
- \[Lib\] Fix leading icon tab display for Innovation Cup theme ([#330](https://github.com/Orange-OpenSource/ods-android/issues/330))
- \[Lib\] Fix a bug where keyboard return key adds a new line in single line `OdsTextField` and `OdsOutlinedTextField` ([#350](https://github.com/Orange-OpenSource/ods-android/issues/350))

### Removed

- \[App\] Removed unused resources ([#352](https://github.com/Orange-OpenSource/ods-android/issues/352))

## [0.7.0](https://github.com/Orange-OpenSource/ods-android/compare/0.6.0...0.7.0) - 2022-11-09

### Added

- \[ThemeConfigurationContract\] Add module to create themes
- \[ThemeOrange\] Add module for Orange theme
- \[ThemeInnovationCup\] Add module for Innovation Cup theme

### Changed

- \[App\] Display an illustration and description header on guideline typography screen ([#301](https://github.com/Orange-OpenSource/ods-android/issues/301))
- \[App\] Use step counter for list item line count customization ([#306](https://github.com/Orange-OpenSource/ods-android/issues/306))
- \[App\] Display the chip name and a short description for each type of chip ([#304](https://github.com/Orange-OpenSource/ods-android/issues/304))

## [0.6.0](https://github.com/Orange-OpenSource/ods-android/compare/0.5.0...0.6.0) - 2022-10-17

### Added

- \[App\] Display composable name associated to each component or variant ([#267](https://github.com/Orange-OpenSource/ods-android/issues/267))

### Changed

- \[App\] Progress & activities components have been split in two variants ([#282](https://github.com/Orange-OpenSource/ods-android/issues/282))
- \[App\] Components detail screens have been homogenized ([#254](https://github.com/Orange-OpenSource/ods-android/issues/254))
- \[App\] Variants displayed in buttons detail screen have been changed ([#260](https://github.com/Orange-OpenSource/ods-android/issues/260), [#278](https://github.com/Orange-OpenSource/ods-android/issues/278))
- \[App\] Expand components customization bottom sheets by default ([#284](https://github.com/Orange-OpenSource/ods-android/issues/284))
- \[App\] Color hex value can now be copied to the clipboard ([#256](https://github.com/Orange-OpenSource/ods-android/issues/256))
- \[Lib\] Cards and buttons composables have been renamed to match with Jetpack compose SDK naming ([#267](https://github.com/Orange-OpenSource/ods-android/issues/267))
- \[Lib\] `OdsButtonContainedStyle` has been renamed into `OdsButtonStyle` ([#260](https://github.com/Orange-OpenSource/ods-android/issues/260))
- \[Lib\] Rename `ChoiceChip` to `OdsChoiceChip` ([#271](https://github.com/Orange-OpenSource/ods-android/issues/271))

### Fixed

- \[App\] Fix state vocalization for customization bottom sheets ([#279](https://github.com/Orange-OpenSource/ods-android/issues/279))
- \[App\] Fix vocalization of counters values on change ([#266](https://github.com/Orange-OpenSource/ods-android/issues/266))
- \[Lib\] Fix state vocalization for chips: selected/not selected ([#280](https://github.com/Orange-OpenSource/ods-android/issues/280))

## [0.5.0](https://github.com/Orange-OpenSource/ods-android/compare/0.4.0...0.5.0) - 2022-09-07

### Added

- \[App\] Allow to enable or disable click on cards in customization bottom sheet ([#247](https://github.com/Orange-OpenSource/ods-android/issues/247))
- \[App\] Add spacing guideline screen ([#211](https://github.com/Orange-OpenSource/ods-android/issues/211))
- \[App\] Add customizable top app bar regular component preview ([#105](https://github.com/Orange-OpenSource/ods-android/issues/105))
- \[Lib\] Add `KeyboardState` utility composable which allows to know if the keyboard is opened or closed in Jetpack Compose applications ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsTextFieldCounter`, a composable to add a counter below a text field ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsPasswordTextField` and `OdsPasswordOutlinedTextField` which are text fields implementations for password entry ([#221](https://github.com/Orange-OpenSource/ods-android/issues/221))
- \[Lib\] Add `OdsChoiceChipsFlowRow`, a composable to display a set of choice chips in a full width flow row ([#169](https://github.com/Orange-OpenSource/ods-android/issues/169))
- \[Lib\] Add `imageBackgroundColor`, `imageContentScale` and `imageAlignment` properties to `OdsCardImageFirst`, `OdsCardSmall` and `OdsCardTitleFirst` ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[Lib\] Add `OdsListItemScope` and `OdsListItemIconType` enum as well as `Modifier.iconType` and `Modifier.divider` methods to configure `OdsListItem` icon type and divider ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))

### Changed

- \[App\] Customization radio buttons has been replaced by chips ([#169](https://github.com/Orange-OpenSource/ods-android/issues/169))
- \[App\] The number of items in bottom navigation can now be changed using plus and minus buttons ([#172](https://github.com/Orange-OpenSource/ods-android/issues/172))
- \[App\] Update lists component UI ([#141](https://github.com/Orange-OpenSource/ods-android/issues/141))
- \[Lib\] Replace `@DrawableRes Int` parameters type by `Painter` for all cards and buttons components ([#262](https://github.com/Orange-OpenSource/ods-android/issues/262))
- \[Lib\] Change `OdsSlider` signature: `leftIconRes` and `rightIconRes` have been replaced respectively by `leftIcon` and `rightIcon` which are `Painter` typed ([#243](https://github.com/Orange-OpenSource/ods-android/issues/243))
- \[Lib\] Change `OdsTopAppBar` signature: `title` parameter type changed to String and `onNavigationIconClick` parameter has been added ([#105](https://github.com/Orange-OpenSource/ods-android/issues/105))

### Fixed

- \[App\] Fix vocalization of icons for sliders with icons ([#243](https://github.com/Orange-OpenSource/ods-android/issues/243))
- \[App\] Fix list item number of lines wording ([#252](https://github.com/Orange-OpenSource/ods-android/issues/252))
- \[App\] Fix a bug where guideline and component images were cropped ([#229](https://github.com/Orange-OpenSource/ods-android/issues/229))
- \[App\] Fix a bug where back button did not collapse expanded bottom sheets ([#238](https://github.com/Orange-OpenSource/ods-android/issues/238))
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
