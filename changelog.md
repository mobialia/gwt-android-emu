# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.7.1] - 2026-07-27

### Fixed

- Attach the `sourcesJar` artifact to the `gwtAndroidEmu` Maven publication so sources are published to GitHub Packages.

## [0.7.0] - 2026-07-26

### Added

- Publish the `androidemu` artifacts to GitHub Packages (`https://maven.pkg.github.com/mobialia/gwt-android-emu`).
- GitHub Action workflow (`.github/workflows/publish.yml`) that publishes to GitHub Packages on release.

### Changed

- Migrated to the latest GWT 2.13.1 (from 2.10.0).
- Migrated to Java 17 (from Java 11).
- Upgraded the Gradle wrapper to 9.6.1 (from 7.5.1).
- Switched the shadow plugin to `com.gradleup.shadow` 9.6.1 (the new home of the shadow plugin, replacing the deprecated
  `com.github.johnrengelman:shadow` 7.1.2).
- Upgraded the Gretty plugin to 5.0.2 (from 4.0.3) for Gradle 9 compatibility.
- Replaced the removed `archivesBaseName` project property with the `base { archivesName = ... }` block.
- Replaced JitPack with GitHub Packages as the primary distribution channel.

### Deprecated

- JitPack distribution is no longer the primary channel; prefer GitHub Packages.

### Documentation

- Updated `readme.md` with the new version badge and GitHub Packages distribution instructions.

## [0.6.0] - 2022-10-08

### Added

- Gradle wrapper.
- Gretty plugin for running the demo web app.
- `SharedPreferences.apply()` method.

### Changed

- Upgraded the build and GWT to 2.10.0.

### Removed

- Bintray distribution, replaced by JitPack.

[Unreleased]: https://github.com/mobialia/gwt-android-emu/compare/v0.7.1...HEAD

[0.7.1]: https://github.com/mobialia/gwt-android-emu/compare/v0.7.0...v0.7.1

[0.7.0]: https://github.com/mobialia/gwt-android-emu/compare/v0.6.0...v0.7.0

[0.6.0]: https://github.com/mobialia/gwt-android-emu/releases/tag/v0.6.0