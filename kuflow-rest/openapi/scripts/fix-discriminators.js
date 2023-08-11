#!/usr/bin/env node

const fs = require('fs')

function fixDiscriminators() {

  {
    // AbstractAudited
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/AbstractAudited.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/name = "Process"/, 'name = "PROCESS"')
    fileAsStr = fileAsStr.replace(/name = "Task"/, 'name = "TASK"')
    fileAsStr = fileAsStr.replace(/name = "Authentication"/, 'name = "AUTHENTICATION"')
    fileAsStr = fileAsStr.replace(/name = "Worker"/, 'name = "WORKER"')

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

  {
    // Worker
    const file = `${process.cwd()}/../src/generated/java/com/kuflow/rest/model/Worker.java`

    const fileData = fs.readFileSync(file)
    let fileAsStr = fileData.toString('utf8')

    fileAsStr = fileAsStr.replace(/@JsonTypeName\("Worker"\)/, '@JsonTypeName("WORKER")')

    fs.writeFileSync(file, fileAsStr, 'utf8')
  }

}

fixDiscriminators()
