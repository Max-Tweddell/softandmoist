(ns bug.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [bug.core-test]))

(doo-tests 'bug.core-test)

