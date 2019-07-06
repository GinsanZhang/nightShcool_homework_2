package com.ginsan;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {

    public static final String EMPTY = "";
    private LocaleUtils localeUtils;

    @Before
    public void setUp() throws Exception {
        localeUtils = new LocaleUtils();
    }

    @Test
    public void should_get_locale_when_arg_only_contains_language_section_and_two_chars() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo(EMPTY);
    }

    @Test
    public void should_get_locale_when_arg_only_contains_language_section_and_three_chars() {
        // given
        // when
        Locale locale = localeUtils.toLocale("usa");
        // then
        assertThat(locale.getLanguage()).isEqualTo("usa");
        assertThat(locale.getCountry()).isEqualTo(EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_locale_when_arg_only_contains_language_section_and_more_than_three_chars() {
        // given
        // when
        Locale locale = localeUtils.toLocale("japan");
        // then
    }

    @Test
    public void should_get_locale_when_arg_only_contains_language_and_country_sections() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_CN");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo("CN");
    }

    @Test
    public void should_get_locale_when_when_country_is_numeric_code() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_457");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo("457");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_country_is_numeric_code_but_incorrect_length() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_45");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_country_is_incorrect_numeric_code() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_45x");
        // then
    }

    @Test
    public void should_get_locale_when_arg_contains_language_and_country_and_variant() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_CN_xxx");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo("CN");
        assertThat(locale.getVariant()).isEqualTo("xxx");
    }

    @Test
    public void should_get_locale_when_arg_contains_language_and_variant() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh__xxx");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo(EMPTY);
        assertThat(locale.getVariant()).isEqualTo("xxx");
    }

    @Test
    public void should_get_locale_when_arg_contains_language_and_variant_and_numeric_country_code() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_111_xxx");
        // then
        assertThat(locale.getLanguage()).isEqualTo("zh");
        assertThat(locale.getCountry()).isEqualTo("111");
        assertThat(locale.getVariant()).isEqualTo("xxx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_contains_all_sections_but_country_is_not_ISO3166() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_11_x");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_contains_all_sections_but_variant_is_empty() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_111_");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_contains_all_sections_but_language_is_not_ISO639() {
        // given
        // when
        Locale locale = localeUtils.toLocale("ZH_111_");
        // then
    }

    @Test
    public void should_get_locale_when_arg_only_contains_country_section() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_CN");
        // then
        assertThat(locale.getLanguage()).isEmpty();
        assertThat(locale.getCountry()).isEqualTo("CN");
    }

    @Test
    public void should_get_locale_when_arg_only_contains_language_and_variant_sections() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_CN_x");
        // then
        assertThat(locale.getLanguage()).isEmpty();
        assertThat(locale.getCountry()).isEqualTo("CN");
        assertThat(locale.getVariant()).isEqualTo("x");
    }

    @Test
    public void should_get_null_when_arg_is_null() {
        // given
        // when
        Locale locale = localeUtils.toLocale(null);
        // then
        assertThat(locale).isNull();
    }

    @Test
    public void should_get_empty_local_when_arg_is_empty_string() {
        // given
        // when
        Locale locale = localeUtils.toLocale(EMPTY);
        // then
        assertThat(locale.getLanguage()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_is_java7_style() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh#CN");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_language_section_but_incorrect_length() {
        // given
        // when
        Locale locale = localeUtils.toLocale("z");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_country_section_but_incorrect_length() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_z");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_country_section_but_is_not_upper_case() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_Zh");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_country_section_but_is_lower_case() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_zh");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_has_country_section_but_length_more_than_two() {
        // given
        // when
        Locale locale = localeUtils.toLocale("zh_USA");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_country_and_variant_sections_but_incorrect_length() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_ZH_");
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_arg_only_has_country_and_variant_sections_but_incorrect_format() {
        // given
        // when
        Locale locale = localeUtils.toLocale("_ZH-xx");
        // then
    }
}