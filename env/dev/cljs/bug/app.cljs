(ns ^:figwheel-no-load bug.app
  (:require [bug.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
