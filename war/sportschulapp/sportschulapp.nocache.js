function sportschulapp(){
  var $intern_0 = '', $intern_34 = '" for "gwt:onLoadErrorFn"', $intern_32 = '" for "gwt:onPropertyErrorFn"', $intern_15 = '"><\/script>', $intern_17 = '#', $intern_42 = '&', $intern_73 = '.cache.html', $intern_19 = '/', $intern_59 = '0529B97AF77959D0EED581F7516AB937', $intern_60 = '2988C456898386D02B8DA1F0018A3261', $intern_62 = '2EEDA7F150EC8B7A00FA081B4AD7DFE6', $intern_63 = '373DBB4A65714322F7B252554A5B049F', $intern_72 = ':', $intern_26 = '::', $intern_82 = '<script defer="defer">sportschulapp.onInjectionDone(\'sportschulapp\')<\/script>', $intern_14 = '<script id="', $intern_29 = '=', $intern_18 = '?', $intern_64 = 'AF2F0AF27AC7BA53D7B02CA66C076472', $intern_65 = 'BA13B78BA311F589363B1CAD098D2D6C', $intern_66 = 'BB813F9FB5E0533ED73CA483B029783D', $intern_67 = 'BF0589F1D855B5F6A3B4BA951AB0D5F3', $intern_31 = 'Bad handler "', $intern_68 = 'CA705415F6CAB0CF73F6C96A5A954A63', $intern_69 = 'CADB304432C2E6FCA6C4894E5837BC3D', $intern_70 = 'CEE887C8C5DA1355832F09C67D2C3F70', $intern_81 = 'DOMContentLoaded', $intern_71 = 'F5FE5457598535D5ED0F841F034B6B33', $intern_16 = 'SCRIPT', $intern_45 = 'Unexpected exception in locale detection, using default: ', $intern_80 = 'Upload.css', $intern_44 = '_', $intern_43 = '__gwt_Locale', $intern_13 = '__gwt_marker_sportschulapp', $intern_20 = 'base', $intern_10 = 'baseUrl', $intern_4 = 'begin', $intern_3 = 'bootstrap', $intern_22 = 'clear.cache.gif', $intern_28 = 'content', $intern_61 = 'de', $intern_40 = 'default', $intern_9 = 'end', $intern_53 = 'gecko', $intern_54 = 'gecko1_8', $intern_5 = 'gwt.codesvr=', $intern_6 = 'gwt.hosted=', $intern_7 = 'gwt.hybrid', $intern_74 = 'gwt/standard/standard.css', $intern_33 = 'gwt:onLoadErrorFn', $intern_30 = 'gwt:onPropertyErrorFn', $intern_27 = 'gwt:property', $intern_79 = 'head', $intern_57 = 'hosted.html?sportschulapp', $intern_78 = 'href', $intern_52 = 'ie6', $intern_51 = 'ie8', $intern_35 = 'iframe', $intern_21 = 'img', $intern_36 = "javascript:''", $intern_75 = 'link', $intern_56 = 'loadExternalRefs', $intern_39 = 'locale', $intern_41 = 'locale=', $intern_23 = 'meta', $intern_38 = 'moduleRequested', $intern_8 = 'moduleStartup', $intern_50 = 'msie', $intern_24 = 'name', $intern_47 = 'opera', $intern_37 = 'position:absolute;width:0;height:0;border:none', $intern_76 = 'rel', $intern_49 = 'safari', $intern_11 = 'script', $intern_58 = 'selectingPermutation', $intern_1 = 'sportschulapp', $intern_12 = 'sportschulapp.nocache.js', $intern_25 = 'sportschulapp::', $intern_2 = 'startup', $intern_77 = 'stylesheet', $intern_55 = 'unknown', $intern_46 = 'user.agent', $intern_48 = 'webkit';
  var $wnd = window, $doc = document, $stats = $wnd.__gwtStatsEvent?function(a){
    return $wnd.__gwtStatsEvent(a);
  }
  :null, $sessionId = $wnd.__gwtStatsSessionId?$wnd.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], softPermutationId = 0, onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_4});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd.location.search;
      return (query.indexOf($intern_5) != -1 || (query.indexOf($intern_6) != -1 || $wnd.external && $wnd.external.gwtOnLoad)) && query.indexOf($intern_7) == -1;
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      sportschulapp = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base, softPermutationId);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_9});
    }
  }

  function computeScriptBase(){
    if (metaProps[$intern_10]) {
      base = metaProps[$intern_10];
      return base;
    }
    var thisScript;
    var scriptTags = $doc.getElementsByTagName($intern_11);
    for (var i = 0; i < scriptTags.length; ++i) {
      if (scriptTags[i].src.indexOf($intern_12) != -1) {
        thisScript = scriptTags[i];
      }
    }
    if (!thisScript) {
      var markerId = $intern_13;
      var markerScript;
      $doc.write($intern_14 + markerId + $intern_15);
      markerScript = $doc.getElementById(markerId);
      thisScript = markerScript && markerScript.previousSibling;
      while (thisScript && thisScript.tagName != $intern_16) {
        thisScript = thisScript.previousSibling;
      }
    }
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf($intern_17);
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf($intern_18);
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf($intern_19, Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):$intern_0;
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == $intern_0) {
      var baseElements = $doc.getElementsByTagName($intern_20);
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement($intern_21);
      img.src = base + $intern_22;
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
    return base;
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_23);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_24), content;
      if (name) {
        name = name.replace($intern_25, $intern_0);
        if (name.indexOf($intern_26) >= 0) {
          continue;
        }
        if (name == $intern_27) {
          content = meta.getAttribute($intern_28);
          if (content) {
            var value, eq = content.indexOf($intern_29);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_30) {
          content = meta.getAttribute($intern_28);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_31 + content + $intern_32);
            }
          }
        }
         else if (name == $intern_33) {
          content = meta.getAttribute($intern_28);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_31 + content + $intern_34);
            }
          }
        }
      }
    }
  }

  function __gwt_isKnownPropertyValue(propName, propValue){
    return propValue in values[propName];
  }

  function __gwt_getMetaProperty(name){
    var value = metaProps[name];
    return value == null?null:value;
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc.createElement($intern_35);
      iframe.src = $intern_36;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_37;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_38});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers[$intern_39] = function(){
    try {
      var locale;
      var defaultLocale = $intern_40 || $intern_40;
      if (locale == null) {
        var args = location.search;
        var startLang = args.indexOf($intern_41);
        if (startLang >= 0) {
          var language = args.substring(startLang);
          var begin = language.indexOf($intern_29) + 1;
          var end = language.indexOf($intern_42);
          if (end == -1) {
            end = language.length;
          }
          locale = language.substring(begin, end);
        }
      }
      if (locale == null) {
        locale = __gwt_getMetaProperty($intern_39);
      }
      if (locale == null) {
        locale = $wnd[$intern_43];
      }
       else {
        $wnd[$intern_43] = locale || defaultLocale;
      }
      if (locale == null) {
        return defaultLocale;
      }
      while (!__gwt_isKnownPropertyValue($intern_39, locale)) {
        var lastIndex = locale.lastIndexOf($intern_44);
        if (lastIndex == -1) {
          locale = defaultLocale;
          break;
        }
         else {
          locale = locale.substring(0, lastIndex);
        }
      }
      return locale;
    }
     catch (e) {
      alert($intern_45 + e);
      return $intern_40;
    }
  }
  ;
  values[$intern_39] = {de:0, 'default':1};
  providers[$intern_46] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf($intern_47) != -1) {
      return $intern_47;
    }
     else if (ua.indexOf($intern_48) != -1) {
      return $intern_49;
    }
     else if (ua.indexOf($intern_50) != -1) {
      if (document.documentMode >= 8) {
        return $intern_51;
      }
       else {
        var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
        if (result && result.length == 3) {
          var v = makeVersion(result);
          if (v >= 6000) {
            return $intern_52;
          }
        }
      }
    }
     else if (ua.indexOf($intern_53) != -1) {
      var result = /rv:([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 1008)
          return $intern_54;
      }
      return $intern_53;
    }
    return $intern_55;
  }
  ;
  values[$intern_46] = {gecko:0, gecko1_8:1, ie6:2, ie8:3, opera:4, safari:5};
  sportschulapp.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  sportschulapp.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_56, millis:(new Date).getTime(), type:$intern_9});
    maybeStartModule();
  }
  ;
  processMetas();
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd.external && ($wnd.external.initModule && $wnd.external.initModule($intern_1))) {
      $wnd.location.reload();
      return;
    }
    initialHtml = $intern_57;
    strongName = $intern_0;
  }
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_58});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers([$intern_40, $intern_47], $intern_59);
      unflattenKeylistIntoAnswers([$intern_40, $intern_53], $intern_60);
      unflattenKeylistIntoAnswers([$intern_61, $intern_49], $intern_62);
      unflattenKeylistIntoAnswers([$intern_61, $intern_51], $intern_63);
      unflattenKeylistIntoAnswers([$intern_40, $intern_52], $intern_64);
      unflattenKeylistIntoAnswers([$intern_40, $intern_49], $intern_65);
      unflattenKeylistIntoAnswers([$intern_61, $intern_52], $intern_66);
      unflattenKeylistIntoAnswers([$intern_61, $intern_47], $intern_67);
      unflattenKeylistIntoAnswers([$intern_40, $intern_54], $intern_68);
      unflattenKeylistIntoAnswers([$intern_40, $intern_51], $intern_69);
      unflattenKeylistIntoAnswers([$intern_61, $intern_53], $intern_70);
      unflattenKeylistIntoAnswers([$intern_61, $intern_54], $intern_71);
      strongName = answers[computePropValue($intern_39)][computePropValue($intern_46)];
      var idx = strongName.indexOf($intern_72);
      if (idx != -1) {
        softPermutationId = Number(strongName.substring(idx + 1));
        strongName = strongName.substring(0, idx);
      }
      initialHtml = strongName + $intern_73;
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded[$intern_74]) {
        var l = $doc.createElement($intern_75);
        __gwt_stylesLoaded[$intern_74] = l;
        l.setAttribute($intern_76, $intern_77);
        l.setAttribute($intern_78, base + $intern_74);
        $doc.getElementsByTagName($intern_79)[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded[$intern_80]) {
        var l = $doc.createElement($intern_75);
        __gwt_stylesLoaded[$intern_80] = l;
        l.setAttribute($intern_76, $intern_77);
        l.setAttribute($intern_78, base + $intern_80);
        $doc.getElementsByTagName($intern_79)[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_81, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_81, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_9});
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_56, millis:(new Date).getTime(), type:$intern_4});
  $doc.write($intern_82);
}

sportschulapp();
