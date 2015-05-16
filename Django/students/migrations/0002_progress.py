# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('students', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Progress',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('HighSchoolTranscript_received', models.IntegerField()),
                ('PersonalInformation_completed', models.IntegerField()),
                ('PreviousEducation_completed', models.IntegerField()),
                ('AcademicMajor_completed', models.IntegerField()),
                ('Citizenship_completed', models.IntegerField()),
                ('Address_completed', models.IntegerField()),
                ('Application_submitted', models.IntegerField()),
                ('student', models.ForeignKey(to='students.Student')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
    ]
