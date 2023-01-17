#!/usr/bin/env node

const fs = require('fs')

function removeUnnecessaryUnionOptions() {

  {
    // AbstractAudited
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/AbstractAudited.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/name = "Process"/, 'name = "PROCESS"')
    fileAsStr = fileAsStr.replace(/name = "Process"/, 'name = "PROCESS"')
    fileAsStr = fileAsStr.replace(/name = "Authentication"/, 'name = "AUTHENTICATION"')

    fs.writeFileSync(file, fileAsStr, 'utf8')
  }

  {
    // Authentication
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/Authentication.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/@JsonTypeName\("Authentication"\)/, '@JsonTypeName("AUTHENTICATION")')

    fs.writeFileSync(file, fileAsStr, 'utf8')
  }

  {
    // Process
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/Process.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/@JsonTypeName\("Process"\)/, '@JsonTypeName("PROCESS")')

    fs.writeFileSync(file, fileAsStr, 'utf8')
  }

  {
    // Task
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/Task.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/@JsonTypeName\("Task"\)/, '@JsonTypeName("TASK")')

    fs.writeFileSync(file, fileAsStr, 'utf8')
  }

}

removeUnnecessaryUnionOptions()
